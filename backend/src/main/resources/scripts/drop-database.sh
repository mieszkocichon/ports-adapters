#!/bin/bash

DB_NAME="car"
DB_USER="postgres"
DB_HOST="localhost"
DB_PORT="5432"
DB_PASSWORD="d2d2d2d2"

export PGPASSWORD="$DB_PASSWORD"

psql -U "$DB_USER" -d postgres -h "$DB_HOST" -p "$DB_PORT" -c "DROP DATABASE IF EXISTS \"$DB_NAME\";"

if [ $? -eq 0 ]; then
    echo "Database '$DB_NAME' deleted successfully."
else
    echo "Failed to delete the database '$DB_NAME'."
fi