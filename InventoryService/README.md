# InventoryService

A Spring Boot microservice for managing product inventory and stock levels using MySQL as the database with Flyway for database migrations.

## Overview

The InventoryService provides RESTful endpoints for managing product inventory in an e-commerce system. It tracks stock levels, handles inventory updates, and provides inventory availability information to other services.

## Features

- ✅ Inventory CRUD operations
- ✅ MySQL integration with Spring Data JPA
- ✅ Database migrations with Flyway
- ✅ RESTful API design
- ✅ OpenAPI 3.0 documentation (Swagger UI)
- ✅ Lombok integration for reduced boilerplate code
- ✅ Testcontainers for integration testing
- ✅ Docker Compose setup for MySQL

## Technology Stack

- **Spring Boot 3.4.8**
- **Spring Data JPA**
- **Spring Web**
- **MySQL 8.0**
- **Flyway** (Database migrations)
- **Lombok 1.18.34**
- **SpringDoc OpenAPI 3**
- **JUnit 5**
- **Testcontainers**
- **Docker & Docker Compose**

## Project Structure

```
InventoryService/
├── src/
│   ├── main/
│   │   ├── java/com/example/microservice/InventoryService/
│   │   │   ├── Controller/
│   │   │   │   └── InventoryController.java
│   │   │   ├── Dto/
│   │   │   │   └── InventoryRequest.java
│   │   │   ├── Models/
│   │   │   │   └── Inventory.java
│   │   │   ├── Repository/
│   │   │   │   └── InventoryRepository.java
│   │   │   ├── Services/
│   │   │   │   └── InventoryService.java
│   │   │   └── InventoryServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker/
│   └── mysql/
│       └── init.sql
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Configuration

### Application Properties
```properties
spring.application.name=InventoryService
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3316/inventory_service
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
server.port=8082
springdoc.swagger-ui.path=/swagger.html
```

### Database Configuration
- **Database**: MySQL 8.0
- **Port**: 3316 (mapped from 3306)
- **Database Name**: inventory_service
- **JDBC URL**: jdbc:mysql://localhost:3316/inventory_service
- **Username**: root
- **Password**: root

## Getting Started

### Prerequisites
- Java 21
- Maven 3.6+
- Docker & Docker Compose

### Running the Service

1. **Start MySQL using Docker Compose**
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

The service will start on `http://localhost:8082`

### Docker Compose Services

The `docker-compose.yml` includes:
- **MySQL 8.0**: Running on port 3316 (host) -> 3306 (container)
- **Container Name**: mysqlInventory
- **Root Password**: root
- **Data Volume**: `./mysql_data`
- **Init Script**: `./docker/mysql/init.sql`

## API Endpoints

### Inventory Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/inventory` | Get all inventory records |
| GET | `/api/inventory/{skuCode}` | Get inventory by SKU code |
| POST | `/api/inventory` | Create new inventory record |
| PUT | `/api/inventory/{skuCode}` | Update inventory |
| DELETE | `/api/inventory/{skuCode}` | Delete inventory record |
| GET | `/api/inventory/check-stock/{skuCode}` | Check stock availability |

### API Documentation

Access the interactive API documentation at:
- **Swagger UI**: http://localhost:8082/swagger.html
- **OpenAPI JSON**: http://localhost:8082/v3/api-docs

## Data Models

### Inventory
```java
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String skuCode;
    private Integer quantity;
    // ... other fields
}
```

### DTOs
- **InventoryRequest**: Input DTO for creating/updating inventory

## Database Migrations

This service uses Flyway for database schema management:

- **Migration Scripts Location**: `src/main/resources/db/migration/`
- **Naming Convention**: `V{version}__{description}.sql`
- **Automatic Migration**: Runs on application startup

### Creating New Migrations
```bash
# Create a new migration file
# Example: V2__Add_reserved_quantity_column.sql
```

## Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run integration tests
mvn test -Dtest="**/*IT"
```

### Test Configuration
- Uses Testcontainers for MySQL integration tests
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

## Integration with Other Services

### OrderService Integration
- OrderService calls InventoryService to check product availability
- Communication is handled through REST API calls
- Inventory checks are performed before order processing

## Monitoring and Health Checks

### Health Endpoint
- **URL**: http://localhost:8082/actuator/health
- **Purpose**: Check service health status

### Application Info
- **URL**: http://localhost:8082/actuator/info
- **Purpose**: Get application information

## Troubleshooting

### Common Issues

1. **MySQL Connection Issues**
   ```bash
   # Check if MySQL container is running
   docker ps
   
   # Check MySQL logs
   docker-compose logs mysql
   
   # Connect to MySQL container
   docker exec -it mysqlInventory mysql -u root -p
   ```

2. **Port Already in Use**
   ```bash
   # Find process using port 8082
   netstat -ano | findstr :8082
   
   # Kill the process (Windows)
   taskkill /PID <PID> /F
   ```

3. **Flyway Migration Issues**
   ```bash
   # Clean and rebuild with fresh database
   docker-compose down -v
   docker-compose up -d
   mvn clean install
   ```

4. **Database Connection Pool Issues**
   ```bash
   # Check database connections
   docker exec -it mysqlInventory mysql -u root -p -e "SHOW PROCESSLIST;"
   ```

### Useful Commands

```bash
# View application logs
docker-compose logs -f

# Stop all services
docker-compose down

# Remove volumes (careful - this will delete data)
docker-compose down -v

# Access MySQL shell
docker exec -it mysqlInventory mysql -u root -p

# View database structure
docker exec -it mysqlInventory mysql -u root -p -e "USE inventory_service; SHOW TABLES; DESCRIBE inventory;"
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8082 | Application port |
| `DB_HOST` | localhost | MySQL host |
| `DB_PORT` | 3316 | MySQL port |
| `DB_NAME` | inventory_service | Database name |
| `DB_USERNAME` | root | Database username |
| `DB_PASSWORD` | root | Database password |
| `SWAGGER_PATH` | /swagger.html | Swagger UI path |

## Performance Considerations

- **Connection Pool**: Configure HikariCP for optimal performance
- **Indexing**: Ensure proper database indexes on frequently queried columns
- **Caching**: Consider implementing Redis for frequently accessed inventory data
- **Batch Operations**: Use batch processing for bulk inventory updates

## Contributing

1. Follow the existing code structure and patterns
2. Add tests for new functionality
3. Create Flyway migration scripts for database changes
4. Update documentation as needed
5. Use Lombok annotations to reduce boilerplate code
6. Follow REST API best practices
7. Ensure proper error handling and logging
