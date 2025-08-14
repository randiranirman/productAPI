# ProductAPI Service

A Spring Boot microservice for managing product catalog and information using MongoDB as the database.

## Overview

The ProductAPI service provides RESTful endpoints for managing products in an e-commerce system. It handles product creation, retrieval, updates, and deletion operations with MongoDB as the persistence layer.

## Features

- ✅ Product CRUD operations
- ✅ MongoDB integration with Spring Data MongoDB
- ✅ RESTful API design
- ✅ OpenAPI 3.0 documentation (Swagger UI)
- ✅ Lombok integration for reduced boilerplate code
- ✅ Testcontainers for integration testing
- ✅ Docker Compose setup for MongoDB

## Technology Stack

- **Spring Boot 3.5.4**
- **Spring Data MongoDB**
- **Spring Web**
- **MongoDB**
- **Lombok**
- **SpringDoc OpenAPI 3**
- **JUnit 5**
- **Testcontainers**
- **Docker & Docker Compose**

## Project Structure

```
productAPI/
├── src/
│   ├── main/
│   │   ├── java/org/moviecrudspring/productapi/
│   │   │   ├── Config/
│   │   │   │   └── OpenAPIConfig.java
│   │   │   ├── Controller/
│   │   │   │   └── ProductController.java
│   │   │   ├── Dtos/
│   │   │   │   ├── ProductRequest.java
│   │   │   │   └── ProductResponse.java
│   │   │   ├── Models/
│   │   │   │   └── Product.java
│   │   │   ├── Repository/
│   │   │   │   └── ProductRepository.java
│   │   │   ├── Services/
│   │   │   │   └── ProductService.java
│   │   │   └── ProductApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Configuration

### Application Properties
```properties
spring.application.name=productAPI
spring.data.mongodb.uri=mongodb://localhost:27017/productDbNew
server.port=8080
springdoc.swagger-ui.path=/swagger.html
```

### Database Configuration
- **Database**: MongoDB
- **Port**: 27017
- **Database Name**: productDbNew
- **Connection URI**: mongodb://localhost:27017/productDbNew

## Getting Started

### Prerequisites
- Java 21
- Maven 3.6+
- Docker & Docker Compose

### Running the Service

1. **Start MongoDB using Docker Compose**
   ```bash
   docker-compose up -d
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The service will start on `http://localhost:8080`

### Docker Compose Services

The `docker-compose.yml` includes:
- **MongoDB**: Latest version running on port 27017
- **Credentials**: root/password
- **Database**: productServiceDB
- **Data Volume**: `./data/mongodb`

## API Endpoints

### Product Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create new product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

### API Documentation

Access the interactive API documentation at:
- **Swagger UI**: http://localhost:8080/swagger.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Data Models

### Product
```java
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String category;
    // ... other fields
}
```

### DTOs
- **ProductRequest**: Input DTO for creating/updating products
- **ProductResponse**: Output DTO for product data

## Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run integration tests
mvn test -Dtest="**/*IT"
```

### Test Configuration
- Uses Testcontainers for MongoDB integration tests
- JUnit 5 for unit and integration testing
- Test containers automatically handle database setup/teardown

## Development

### Building the Project
```bash
mvn clean compile
```

### Packaging
```bash
mvn clean package
```

### Running in Development Mode
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Monitoring and Health Checks

### Health Endpoint
- **URL**: http://localhost:8080/actuator/health
- **Purpose**: Check service health status

### Application Info
- **URL**: http://localhost:8080/actuator/info
- **Purpose**: Get application information

## Troubleshooting

### Common Issues

1. **MongoDB Connection Issues**
   ```bash
   # Check if MongoDB container is running
   docker ps
   
   # Check MongoDB logs
   docker-compose logs mongodb
   ```

2. **Port Already in Use**
   ```bash
   # Find process using port 8080
   netstat -ano | findstr :8080
   
   # Kill the process (Windows)
   taskkill /PID <PID> /F
   ```

3. **Build Issues**
   ```bash
   # Clean and rebuild
   mvn clean install -U
   ```

### Useful Commands

```bash
# View application logs
docker-compose logs -f

# Stop all services
docker-compose down

# Remove volumes (careful - this will delete data)
docker-compose down -v

# Access MongoDB shell
docker exec -it mongodbreal mongo -u root -p password
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8080 | Application port |
| `MONGODB_URI` | mongodb://localhost:27017/productDbNew | MongoDB connection string |
| `SWAGGER_PATH` | /swagger.html | Swagger UI path |

## Contributing

1. Follow the existing code structure and patterns
2. Add tests for new functionality
3. Update documentation as needed
4. Use Lombok annotations to reduce boilerplate code
5. Follow REST API best practices
