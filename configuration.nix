{ config, pkgs, ... }:

{
  # Enable PostgreSQL service
  services.postgresql.enable = true;
  services.postgresql.package = pkgs.postgresql_14; # Specify the version you want
  services.postgresql.initialScript = ''
    CREATE DATABASE car;
    CREATE USER m2m WITH PASSWORD 'd2d2d2d2';
    GRANT ALL PRIVILEGES ON DATABASE car TO m2m;
  '';

  # Set up the Java environment
  environment.systemPackages = with pkgs; [
    openjdk17  # or your preferred version of OpenJDK
    maven      # Optional: if you want to build the project on the server
  ];

  # Optional: Configure firewall to allow PostgreSQL connections
  networking.firewall.allowedTCPPorts = [ 5432 ]; # PostgreSQL default port

  # Optional: Configure systemd service for your Spring Boot application
  systemd.services.spring-boot-app = {
    description = "Spring Boot Application";
    after = [ "postgresql.service" ];
    wants = [ "postgresql.service" ];
    serviceConfig = {
      ExecStart = "${pkgs.openjdk17}/bin/java -jar /path/to/your/demo.jar"; # Update with the actual path to your JAR
      Restart = "always";
      User = "your-username"; # Replace with the user that should run the app
      Environment = "SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/car";
      Environment = "SPRING_DATASOURCE_USERNAME=m2m";
      Environment = "SPRING_DATASOURCE_PASSWORD=d2d2d2d2";
    };
    wantedBy = [ "multi-user.target" ];
  };
}