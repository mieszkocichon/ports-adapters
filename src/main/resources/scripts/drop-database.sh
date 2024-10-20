#!/bin/bash

# Variables
DB_NAME="car"           # Replace with your database name
DB_USER="postgres" # Replace with your PostgreSQL username
DB_HOST="localhost"     # Replace with your PostgreSQL host if necessary
DB_PORT="5432"          # Replace with your PostgreSQL port if necessary
DB_PASSWORD="mmm" # Replace with your PostgreSQL password

# Set PGPASSWORD environment variable
export PGPASSWORD="$DB_PASSWORD"

# Connect to PostgreSQL and delete the database
psql -U "$DB_USER" -d postgres -h "$DB_HOST" -p "$DB_PORT" -c "DROP DATABASE IF EXISTS \"$DB_NAME\";"

if [ $? -eq 0 ]; then
    echo "Database '$DB_NAME' deleted successfully."
else
    echo "Failed to delete the database '$DB_NAME'."
fi