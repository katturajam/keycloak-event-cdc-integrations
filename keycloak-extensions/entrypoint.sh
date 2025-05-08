#!/bin/bash

# Copy only .jar files (check if the files exist first)
if [ -d "/opt/keycloak-extensions/target" ]; then
  cp /opt/keycloak-extensions/target/*.jar /opt/keycloak/providers/
fi

# Execute the Keycloak startup command
exec /opt/keycloak/bin/kc.sh start-dev
