# Fitness App Microservices 🏋️‍♀️

A microservices-based fitness application secured using Keycloak and OAuth2.

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Cloud (Config, Eureka, Gateway)
- Keycloak (OAuth2 / OpenID Connect)
- MySQL
- Maven
- Docker (for Keycloak)

## 🧩 Architecture
- Config Server
- Eureka Service Registry
- API Gateway
- User Service
- Fitness Service
- Keycloak Authentication Server

## 🔐 Authentication Flow
1. User authenticates via Keycloak
2. Keycloak issues JWT token
3. API Gateway forwards token to services
4. Services validate token using Keycloak

## 🚀 How to Run the Project

### 1. Start Keycloak
```bash
docker run -p 8080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:latest start-dev
