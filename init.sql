-- init.sql
DROP DATABASE IF EXISTS postgres;

-- Enable logical replication for Debezium
ALTER SYSTEM SET wal_level = logical;

-- Allows concurrent logical replication connections
ALTER SYSTEM SET max_replication_slots = 1;

-- Needed for streaming WALs to subscribers
ALTER SYSTEM SET max_wal_senders = 1;

-- Reload Postgres configuration
SELECT pg_reload_conf();

-- Create publication for Debezium
CREATE PUBLICATION debezium_keycloak_pub FOR ALL TABLES;
-- CREATE PUBLICATION debezium_keycloak_pub FOR public.user_entity, public.org, public.realm;
-- CREATE A REPLICATION slot (used by Debezium).
SELECT * FROM pg_create_logical_replication_slot('debezium_keycloak_slot', 'pgoutput');

ALTER TABLE IF EXISTS public.user_entity REPLICA IDENTITY FULL;
ALTER TABLE IF EXISTS public.org REPLICA IDENTITY FULL;
ALTER TABLE IF EXISTS public.realm REPLICA IDENTITY FULL;