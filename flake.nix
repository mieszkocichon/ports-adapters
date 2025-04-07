{
  description = "PostgreSQL and PGAdmin services";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};

        # Create a Python environment with Flask
        pythonWithFlask = pkgs.python3.withPackages (ps: [ ps.flask ]);

        # Create pgAdmin configuration file
        pgadminConfigFile = pkgs.writeTextFile {
          name = "config_local.py";
          text = ''
            # Custom pgAdmin4 configuration
            import os

            # Set session directory to use our custom path
            SESSION_DB_PATH = os.path.expanduser('~/pgadmin-data/sessions')
            STORAGE_DIR = os.path.expanduser('~/pgadmin-data/storage')
          '';
        };

        # Create a script to start both PostgreSQL and PGAdmin
        startScript = pkgs.writeShellScriptBin "start-postgres-pgadmin" ''
          # Check if running as root/sudo
          if [ "$EUID" -ne 0 ]; then
            echo "This script needs to be run as root for pgAdmin directory permissions."
            echo "Restarting with sudo..."
            exec sudo "$0" "$@"
            exit $?
          fi

          echo "Starting PostgreSQL and PGAdmin4..."

          # Get the original user from the SUDO_USER environment variable
          ACTUAL_USER=''${SUDO_USER:-$(whoami)}
          ACTUAL_HOME=$(eval echo ~$ACTUAL_USER)

          # Create system pgAdmin directory with proper permissions
          echo "Setting up pgAdmin system directories..."
          mkdir -p /var/lib/pgadmin/sessions
          mkdir -p /var/lib/pgadmin/storage
          chmod -R 777 /var/lib/pgadmin

          # Create data directories in user's home directory
          DATA_DIR="$ACTUAL_HOME/postgres-data"
          SOCKET_DIR="$ACTUAL_HOME/postgres-socket"
          PGADMIN_DIR="$ACTUAL_HOME/pgadmin-data"
          LOG_FILE="$ACTUAL_HOME/postgres.log"
          CONFIG_FILE="$ACTUAL_HOME/pgadmin_config_local.py"

          mkdir -p $DATA_DIR
          mkdir -p $SOCKET_DIR
          mkdir -p $PGADMIN_DIR/sessions
          mkdir -p $PGADMIN_DIR/storage

          # Ensure correct ownership
          chown -R $ACTUAL_USER:$(id -gn $ACTUAL_USER) $DATA_DIR
          chown -R $ACTUAL_USER:$(id -gn $ACTUAL_USER) $SOCKET_DIR
          chown -R $ACTUAL_USER:$(id -gn $ACTUAL_USER) $PGADMIN_DIR

          # Copy the pgAdmin config file to user's home directory
          cp ${pgadminConfigFile} $CONFIG_FILE
          chown $ACTUAL_USER:$(id -gn $ACTUAL_USER) $CONFIG_FILE

          # Configure pgAdmin paths
          echo "SESSION_DB_PATH = '/var/lib/pgadmin/sessions'" > $CONFIG_FILE
          echo "STORAGE_DIR = '/var/lib/pgadmin/storage'" >> $CONFIG_FILE

          # Initialize PostgreSQL if needed
          if [ ! -f "$DATA_DIR/PG_VERSION" ]; then
            echo "Initializing PostgreSQL database in $DATA_DIR..."
            su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/initdb -D $DATA_DIR --auth=trust --no-locale --encoding=UTF8"
          fi

          # Update PostgreSQL configuration
          echo "# Custom configuration" > $DATA_DIR/postgresql.conf.custom
          echo "listen_addresses = '127.0.0.1'" >> $DATA_DIR/postgresql.conf.custom
          echo "port = 5432" >> $DATA_DIR/postgresql.conf.custom
          echo "unix_socket_directories = '$SOCKET_DIR'" >> $DATA_DIR/postgresql.conf.custom

          # Check if include directive already exists
          if ! grep -q "postgresql.conf.custom" $DATA_DIR/postgresql.conf; then
            echo "include = 'postgresql.conf.custom'" >> $DATA_DIR/postgresql.conf
          fi

          # Set up credentials
          echo "# Allow connections with password" > $DATA_DIR/pg_hba.conf
          echo "host all all 127.0.0.1/32 trust" >> $DATA_DIR/pg_hba.conf
          echo "local all all trust" >> $DATA_DIR/pg_hba.conf

          # Check for existing PostgreSQL server
          PG_PID=$(lsof -t -i:5432 2>/dev/null)
          if [ ! -z "$PG_PID" ]; then
            echo "PostgreSQL is already running with PID $PG_PID. Stopping it first..."
            kill $PG_PID
            sleep 2
          fi

          # Clean up any stale socket files
          rm -f $SOCKET_DIR/.s.PGSQL.*

          # Start PostgreSQL in background as the original user
          echo "Starting PostgreSQL server..."
          su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/pg_ctl -D $DATA_DIR -l $LOG_FILE start"

          # Wait for PostgreSQL to start (up to 10 seconds)
          echo "Waiting for PostgreSQL to start..."
          for i in {1..10}; do
            if su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/pg_isready -h localhost -q"; then
              echo "PostgreSQL started successfully!"
              break
            fi
            if [ $i -eq 10 ]; then
              echo "Failed to start PostgreSQL. Check the log at $LOG_FILE"
              echo "Last 10 lines of the log:"
              tail -10 $LOG_FILE
              exit 1
            fi
            echo "Waiting... ($i/10)"
            sleep 1
          done

          # Set PGHOST environment variable to use TCP instead of Unix socket
          export PGHOST=localhost

          # Create the user and database
          echo "Setting up database..."
          su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/createuser -h localhost -s postgres 2>/dev/null || echo 'User postgres already exists'"
          su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/psql -h localhost -c \"ALTER USER postgres WITH PASSWORD 'd2d2d2d2';\" postgres"
          su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/createdb -h localhost -U postgres car 2>/dev/null || echo 'Database car already exists'"

          # Import initialization SQL if it exists
          if [ -f ./init.sql ]; then
            echo "Importing initialization SQL..."
            su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/psql -h localhost -U postgres -d car -f ./init.sql"
          fi

          # Start pgAdmin directly with its own binary
          echo "Starting PGAdmin4 on http://localhost:8081"
          echo "Login with admin@pgadmin.com / admin"
          echo ""
          echo "To connect to PostgreSQL from PGAdmin:"
          echo "1. Right-click on 'Servers' and select 'Create > Server'"
          echo "2. Give it a name (e.g. 'Local PostgreSQL')"
          echo "3. On Connection tab, set:"
          echo "   - Host: localhost"
          echo "   - Port: 5432"
          echo "   - Username: postgres"
          echo "   - Password: d2d2d2d2"
          echo ""
          echo "Press Ctrl+C to stop both PostgreSQL and PGAdmin when done"
          echo ""

          # Setup the pgAdmin environment variables
          export PGADMIN_SETUP_EMAIL=admin@pgadmin.com
          export PGADMIN_SETUP_PASSWORD=admin
          export PGADMIN_CONFIG_LOCAL_PATH=$CONFIG_FILE

          # Start pgAdmin4 in the background
          cd $PGADMIN_DIR
          ${pkgs.pgadmin4}/bin/pgadmin4 &
          PGADMIN_PID=$!

          # Create a trap to handle Ctrl+C and clean up properly
          trap "kill $PGADMIN_PID 2>/dev/null; su $ACTUAL_USER -c '${pkgs.postgresql_16}/bin/pg_ctl -D $DATA_DIR stop'; exit 0" INT TERM

          # Wait for pgAdmin to exit or for the user to press Ctrl+C
          wait $PGADMIN_PID || true

          # Clean up when the user presses Ctrl+C or pgAdmin exits
          echo "Stopping PostgreSQL..."
          su $ACTUAL_USER -c "${pkgs.postgresql_16}/bin/pg_ctl -D $DATA_DIR stop"
        '';
      in
      {
        # Define the default package
        packages.default = startScript;

        # Keep the development shell
        devShell = pkgs.mkShell {
          buildInputs = with pkgs; [
            postgresql_16
            pgadmin4
            pythonWithFlask
            lsof
            startScript
          ];
        };

        # Keep the NixOS module
        nixosModules.default = { config, lib, pkgs, ... }: {
          services.postgresql = {
            enable = true;
            package = pkgs.postgresql_16;
            ensureDatabases = [ "car" ];
            enableTCPIP = true;
            authentication = pkgs.lib.mkOverride 10 ''
              local all all trust
              host all all 127.0.0.1/32 trust
              host all all ::1/128 trust
            '';
            initialScript = pkgs.writeText "init.sql" (builtins.readFile ./init.sql);
            settings = {
              listen_addresses = "localhost";
              port = 5432;
            };
          };

          services.pgadmin = {
            enable = true;
            initialEmail = "admin@pgadmin.com";
            initialPasswordFile = pkgs.writeText "pgadmin-pass" "admin";
            port = 8081;
          };

          users.users.postgres = {
            name = "postgres";
            password = "d2d2d2d2";
          };

          networking.firewall.allowedTCPPorts = [ 5432 8081 ];
        };
      }
    );
}