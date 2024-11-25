#!/bin/bash

DB_NAME="car"
DB_USER="postgres"
DB_HOST="localhost"
DB_PORT="5432"
DB_PASSWORD="d2d2d2d2"

TABLE_NAME="car"

export PGPASSWORD=$DB_PASSWORD
psql -h $DB_HOST -p $DB_PORT -U $DB_USER -d $DB_NAME -c "TRUNCATE TABLE $TABLE_NAME RESTART IDENTITY;"

if [ $? -eq 0 ]; then
    echo "Table $TABLE_NAME cleared successfully."
else
    echo "Failed to clear table $TABLE_NAME."
fi