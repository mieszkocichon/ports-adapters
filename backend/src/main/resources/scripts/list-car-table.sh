#!/bin/bash

DB_NAME="car"
DB_USER="postgres"
DB_HOST="localhost"
DB_PORT="5432"

psql -U "$DB_USER" -h "$DB_HOST" -p "$DB_PORT" -d "$DB_NAME" -c "
SELECT * FROM car;
"
