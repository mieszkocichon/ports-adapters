#!/bin/bash

# Variables
DB_NAME="car"           # Replace with your database name
DB_USER="postgres" # Replace with your PostgreSQL username
DB_HOST="localhost"     # Replace with your PostgreSQL host if necessary
DB_PORT="5432"          # Replace with your PostgreSQL port if necessary
DB_PASSWORD="mmm" # Replace with your PostgreSQL password

# Set PGPASSWORD environment variable for authentication
export PGPASSWORD="$DB_PASSWORD"

# Check if the database already exists
if psql -U "$DB_USER" -h "$DB_HOST" -p "$DB_PORT" -d postgres -c "SELECT 1 FROM pg_database WHERE datname='$DB_NAME';" | grep -q 1; then
    echo "Database '$DB_NAME' already exists."
else
    # Connect to PostgreSQL and create the database
    psql -U "$DB_USER" -h "$DB_HOST" -p "$DB_PORT" -d postgres -c "CREATE DATABASE \"$DB_NAME\";"

    if [ $? -eq 0 ]; then
        echo "Database '$DB_NAME' created successfully."
    else
        echo "Failed to create the database '$DB_NAME'."
    fi
fi
