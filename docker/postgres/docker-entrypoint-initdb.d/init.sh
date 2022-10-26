#!/bin/bash

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

        CREATE USER customer WITH PASSWORD 'customer';
        ALTER USER customer WITH SUPERUSER;

        CREATE DATABASE customer_db;
        GRANT ALL PRIVILEGES ON DATABASE customer_db TO customer;

EOSQL
