## About
This project sets up a development environment for Keycloak with PostgreSQL, Kafka, and Debezium, using Docker Compose. It enables identity management (via Keycloak), data persistence (via PostgreSQL), event streaming (via Kafka), and change data capture (via Debezium). It also includes management tools like pgAdmin for database browsing and Kafdrop for Kafka topic inspection. A custom gRPC server is also integrated with Keycloak for custom event handling or logic.

## Core components:

- Keycloak for authentication and authorization

- PostgreSQL as Keycloak’s database

- Kafka + Zookeeper for event streaming

- Debezium Connect to capture DB changes and stream to Kafka

- pgAdmin and Kafdrop for UI-based management

- gRPC microservice to support custom integrations with Keycloak

This setup is ideal for developing and testing event-driven architectures with identity and access management features.

## Architecture Diagram
![image](https://github.com/user-attachments/assets/e844268b-d69b-4683-9f0d-c0595b25bc27)

## Prerequiste:
- Used python version 3.13.3
- Already keycloak compiled `kcl-event-listener-1.0.0.jar` exists in the directory `keycloak-extensions` if there is any change in the `protos` please compile it, For compilation requires
  - Java
  - Maven
- Docker Desktop
- Docker Compose
- Before start the server set environment variable for WEBHOOK_URL in the service `keycloak-server` wants to enable the http event listener

# STEPS
## 1. Create keycloak event listener plugin
  - Follow readme steps in the directory `keycloak-extensions`

## 2. Create GRPC server
  - Follow readme steps in the directory  `kcl-event-grpc-server`

## 3. Build and Start the server
```bash
docker compose up --build
```

## 4. Down the server with clear volume
```bash
docker compose down -v
```


## 5. Build and start the server in daemon mode
```bash
docker compose up -d
```

# 3.1 To create CDC events
 - Complete Above 1 - 3 steps
## 3.2  Create debezium connector for Change Data capture
```bash
./debezium-connector/connector.sh 
```
## 3.3 Listen Kafka stream with Change Data Capture
 - Install python requirements for `python-kafka-consumer` and then start consumer
```
python python-kafka-consumer/consumer.py
```

