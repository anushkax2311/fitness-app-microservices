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

```
  1. Access Keycloak Admin: http://localhost:8080
  2. Username: admin
  3. Password: admin
     
### 2. Configure Keycloak
- Create Realm: fitness-realm
- Create Client:
  1. Client ID: fitness-client
  2. Client Type: Public
  3. Redirect URI: http://localhost:5173/*

### 3. Start Config Server
```
cd configserver
mvn spring-boot:run
```
### 4. Start Eureka Server
```
cd eureka
mvn spring-boot:run
```
### 5. Start Database
#### Ensure MySQL is running with the following configuration:
1. Port: 3306
2. Database: fitness_db
3. Username: root
4. Password: password
### 6. Start API Gateway
```
cd gateway
mvn spring-boot:run
```
### 7. Start Services
```
cd UserService-1
mvn spring-boot:run
```
```
cd ActivityService
mvn spring-boot:run
```
```
cd AiService
mvn spring-boot:run
```
### 8. Start Frontend
```
cd fitness-app-frontend
npm install
npm run dev
```
## ✅ Verify Authentication
1. Login via frontend
2. JWT token is issued by Keycloak
3. Call protected APIs via API Gateway
4. Unauthorized requests return 401
