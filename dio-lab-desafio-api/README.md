SDW Simple API

Simple REST API in Java 17 + Spring Boot 3 + Spring Data JPA + OpenAPI (Swagger).

Endpoints
- GET /users
- GET /users/{id}
- POST /users
- PUT /users/{id}
- DELETE /users/{id}

Swagger
- http://localhost:8080/swagger-ui.html

Run locally
- ./gradlew bootRun

H2 console
- http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:sdwdb
- user: sa
- password: (empty)
