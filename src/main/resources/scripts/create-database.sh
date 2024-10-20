#!/bin/bash

DB_NAME="car"
DB_USER="postgres"
DB_HOST="localhost"
DB_PORT="5432"
DB_PASSWORD="d2d2d2d2"

export PGPASSWORD="$DB_PASSWORD"

if psql -U "$DB_USER" -h "$DB_HOST" -p "$DB_PORT" -d postgres -c "SELECT 1 FROM pg_database WHERE datname='$DB_NAME';" | grep -q 1; then
    echo "Database '$DB_NAME' already exists."
else
    psql -U "$DB_USER" -h "$DB_HOST" -p "$DB_PORT" -d postgres -c "CREATE DATABASE \"$DB_NAME\";"

    if [ $? -eq 0 ]; then
        echo "Database '$DB_NAME' created successfully."
    else
        echo "Failed to create the database '$DB_NAME'."
    fi
fi
