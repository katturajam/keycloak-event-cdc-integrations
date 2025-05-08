## 1. Generate Java class from proto
```bash
protoc \
  -I=protos \
  --java_out=keycloak-extensions/src/main/java \
  --grpc-java_out=keycloak-extensions/src/main/java \
  kcl_event.proto
```

## 2. Install maven dependencies
```bash
mvn install -f pom.xml
```

## 3. Clean existing jar
```bash
mvn clean -f pom.xml
```

## 4. Create Package .jar
```bash
mvn package -f pom.xml
```