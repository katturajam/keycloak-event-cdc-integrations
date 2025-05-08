#!/bin/bash

set -e

# Wait until Connect is ready
echo "Waiting for Kafka Connect to be ready..."
while ! curl -s http://localhost:8083/; do
  sleep 2
done

# Register the connector
echo "Registering Debezium Connector..."
curl --fail -X POST -H "Content-Type: application/json" --data @debezium-connector/keycloak-connector-config.json http://localhost:8083/connectors | jq

# Check connector status
echo "Connector status:"
curl --fail http://localhost:8083/connectors/keycloak-connector/status | jq
