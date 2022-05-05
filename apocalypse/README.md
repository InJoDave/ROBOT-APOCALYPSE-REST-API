# ROBOT APOCALYPSE REST API with Spring Boot, H2 DB, Spring Data JPA

## Steps to Setup

**1. Build and run the app using maven**

```bash
mvn package
java -jar target/apocalypse-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.


**2. Connect to H2 Database**

```bash
Once the application is started you can go to this URL http://localhost:8080/h2-ui and access the database on the web browser

DB URL : jdbc:h2:mem:testdb
Username : sa
Password : password
```
## Explore Rest APIs

**Swagger UI has been configured to list all endpoints at URL http://localhost:8080/swagger-ui/**

The app defines following CRUD APIs.

    1. Get all survivors - GET /api/survivors
    
    2. Add survivors to the database - POST /api/survivor
    
    3. Update survivor location - PUT /api/survivor/{id} | Add location data in Request Body
    
    4. Flag survivor as infected - PUT /api/survivor/{id}/reportInfected
    
    5. List of infected survivors - GET /api/survivors/infected
    
    6. List of non-infected survivors - GET /api/survivors/notInfected
    
    7. Percentage of infected survivors - GET /api/survivors/infectedAsPercentage
    
    8. Percentage of non-infected survivors - GET /api/survivors/notInfectedAsPercentage
    
    9. List of robots - GET /api/robots
    