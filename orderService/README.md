# OrderService

A Spring Boot microservice for handling order processing and management using MySQL as the database with Spring Cloud OpenFeign for inter-service communication.

## Overview

The OrderService provides RESTful endpoints for managing orders in an e-commerce system. It handles order creation, retrieval, updates, and communicates with the InventoryService to check product availability before processing orders.

## Features

- ✅ Order CRUD operations
- ✅ MySQL integration with Spring Data JPA
- ✅ Database migrations with Flyway
- ✅ Inter-service communication with OpenFeign
- ✅ RESTful API design
- ✅ OpenAPI 3.0 documentation (Swagger UI)
- ✅ Lombok integration for reduced boilerplate code
- ✅ Testcontainers for integration testing
- ✅ Docker Compose setup for MySQL
- ✅ Spring Cloud integration

## Technology Stack

- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Web**
- **Spring Cloud OpenFeign** (for service communication)
- **MySQL 8.0**
- **Flyway** (Database migrations)
- **Lombok 1.18.32**
- **SpringDoc OpenAPI 3**
- **JUnit 5**
- **Testcontainers**
- **Docker & Docker Compose**

## Project Structure

```
orderService/
├── src/
│   ├── main/
│   │   ├── java/org/moviecrudspring/orderservice/
│   │   │   ├── Client/
│   │   │   │   └── InventoryClient.java
│   │   │   ├── Controller/
│   │   │   │   └── OrderController.java
│   │   │   ├── Dtos/
│   │   │   │   └── OrderRequest.java
│   │   │   ├── Models/
│   │   │   │   └── Order.java
│   │   │   ├── Repository/
│   │   │   │   └── OrderRepository.java
│   │   │   ├── Service/
│   │   │   │   └── OrderService.java
│   │   │   └── OrderServiceApplication.java
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
spring.application.name=orderService
spring.datasource.password=root
spring.datasource.username=root
spring.datasource.url=jdbc:mysql://localhost:3306/orderdbNew
spring.jpa.hibernate.ddl-auto=none
server.port=8083
springdoc.swagger-ui.path=/swagger.html
```

### Database Configuration
- **Database**: MySQL 8.0
- **Port**: 3306
- **Database Name**: orderdbNew
- **JDBC URL**: jdbc:mysql://localhost:3306/orderdbNew
- **Username**: root
- **Password**: root

## Getting Started

### Prerequisites
- Java 21
- Maven 3.6+
- Docker & Docker Compose
- InventoryService (for full functionality)

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

The service will start on `http://localhost:8083`

### Docker Compose Services

The `docker-compose.yml` includes:
- **MySQL 8.0**: Running on port 3306
- **Container Name**: mysqlnew
- **Root Password**: root
- **Data Volume**: `./mysql_data`
- **Init Script**: `./docker/mysql/init.sql`

## API Endpoints

### Order Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| POST | `/api/orders` | Create new order |
| PUT | `/api/orders/{id}` | Update order |
| DELETE | `/api/orders/{id}` | Delete order |
| GET | `/api/orders/user/{userId}` | Get orders by user ID |

### API Documentation

Access the interactive API documentation at:
- **Swagger UI**: http://localhost:8083/swagger.html
- **OpenAPI JSON**: http://localhost:8083/v3/api-docs

## Data Models

### Order
```java
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String orderNumber;
    private String skuCode;
    private Integer quantity;
    private BigDecimal price;
    // ... other fields
}
```

### DTOs
- **OrderRequest**: Input DTO for creating/updating orders

## Inter-Service Communication

### InventoryClient (OpenFeign)

The OrderService communicates with InventoryService using OpenFeign:

```java
@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {
    @GetMapping("/api/inventory/check-stock/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode);
}
```

### Service Integration Flow
1. **Order Creation**: Client submits order request
2. **Inventory Check**: OrderService calls InventoryService to verify stock
3. **Order Processing**: If stock available, order is created
4. **Response**: Order confirmation returned to client

## Database Migrations

This service uses Flyway for database schema management:

- **Migration Scripts Location**: `src/main/resources/db/migration/`
- **Naming Convention**: `V{version}__{description}.sql`
- **Automatic Migration**: Runs on application startup

### Creating New Migrations
```bash
# Create a new migration file
# Example: V2__Add_order_status_column.sql
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
- Mock external service calls for isolated testing

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

## Service Dependencies

### Required Services
- **InventoryService**: Must be running on port 8082 for order processing
- **MySQL**: Database must be accessible

### Startup Order
1. Start MySQL database
2. Start InventoryService
3. Start OrderService

## Monitoring and Health Checks

### Health Endpoint
- **URL**: http://localhost:8083/actuator/health
- **Purpose**: Check service health status

### Application Info
- **URL**: http://localhost:8083/actuator/info
- **Purpose**: Get application information

## Circuit Breaker and Resilience

### OpenFeign Configuration
- Timeout settings for external calls
- Retry mechanisms for failed requests
- Fallback methods for service unavailability

### Error Handling
- Graceful handling of InventoryService downtime
- Proper error messages for client applications
- Logging of service communication issues

## Troubleshooting

### Common Issues

1. **MySQL Connection Issues**
   ```bash
   # Check if MySQL container is running
   docker ps
   
   # Check MySQL logs
   docker-compose logs mysql
   
   # Connect to MySQL container
   docker exec -it mysqlnew mysql -u root -p
   ```

2. **InventoryService Communication Issues**
   ```bash
   # Check if InventoryService is running
   curl http://localhost:8082/actuator/health
   
   # Check network connectivity
   telnet localhost 8082
   ```

3. **Port Already in Use**
   ```bash
   # Find process using port 8083
   netstat -ano | findstr :8083
   
   # Kill the process (Windows)
   taskkill /PID <PID> /F
   ```

4. **Flyway Migration Issues**
   ```bash
   # Clean and rebuild with fresh database
   docker-compose down -v
   docker-compose up -d
   mvn clean install
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
docker exec -it mysqlnew mysql -u root -p

# View database structure
docker exec -it mysqlnew mysql -u root -p -e "USE orderdbNew; SHOW TABLES; DESCRIBE orders;"

# Test InventoryService connectivity
curl http://localhost:8082/actuator/health
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 8083 | Application port |
| `DB_HOST` | localhost | MySQL host |
| `DB_PORT` | 3306 | MySQL port |
| `DB_NAME` | orderdbNew | Database name |
| `DB_USERNAME` | root | Database username |
| `DB_PASSWORD` | root | Database password |
| `INVENTORY_SERVICE_URL` | http://localhost:8082 | InventoryService URL |
| `SWAGGER_PATH` | /swagger.html | Swagger UI path |

## Performance Considerations

- **Connection Pool**: Configure HikariCP for optimal database performance
- **Feign Client**: Configure timeouts and retry policies appropriately
- **Indexing**: Ensure proper database indexes on frequently queried columns
- **Caching**: Consider implementing Redis for frequently accessed data
- **Async Processing**: Use async processing for non-critical operations

## Security Considerations

- **Input Validation**: Validate all incoming requests
- **SQL Injection**: Use parameterized queries (handled by JPA)
- **Service Communication**: Secure inter-service communication
- **Authentication**: Implement proper authentication/authorization

## Contributing

1. Follow the existing code structure and patterns
2. Add tests for new functionality
3. Create Flyway migration scripts for database changes
4. Update documentation as needed
5. Use Lombok annotations to reduce boilerplate code
6. Follow REST API best practices
7. Ensure proper error handling and logging
8. Test inter-service communication thoroughly
9. Consider circuit breaker patterns for external calls
