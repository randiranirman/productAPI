# Spring Boot Microservices Project

This project contains a collection of Spring Boot microservices that work together to provide a complete e-commerce solution with product management, inventory tracking, and order processing capabilities.

## Architecture Overview

The project consists of three main microservices:

- **ProductAPI** - Product catalog management service (MongoDB)
- **InventoryService** - Inventory management service (MySQL)
- **OrderService** - Order processing service (MySQL)

## Services

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| ProductAPI | 8080 | MongoDB (27017) | Manages product catalog and information |
| InventoryService | 8082 | MySQL (3316) | Tracks product inventory and stock levels |
| OrderService | 8083 | MySQL (3306) | Handles order creation and management |

## Prerequisites

- Java 21
- Maven 3.6+
- Docker & Docker Compose
- MongoDB
- MySQL

## Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Project
   ```

2. **Start the databases using Docker Compose**
   ```bash
   # Start MongoDB for ProductAPI
   cd productAPI && docker-compose up -d
   
   # Start MySQL for InventoryService
   cd ../InventoryService && docker-compose up -d
   
   # Start MySQL for OrderService
   cd ../orderService && docker-compose up -d
   ```

3. **Build and run each service**
   ```bash
   # Build all services
   mvn clean install -f productAPI/pom.xml
   mvn clean install -f InventoryService/pom.xml
   mvn clean install -f orderService/pom.xml
   
   # Run services (in separate terminals)
   cd productAPI && mvn spring-boot:run
   cd InventoryService && mvn spring-boot:run
   cd orderService && mvn spring-boot:run
   ```

## API Documentation

Each service provides Swagger UI documentation:

- **ProductAPI**: http://localhost:8080/swagger.html
- **InventoryService**: http://localhost:8082/swagger.html
- **OrderService**: http://localhost:8083/swagger.html

## Service Communication

- **OrderService** communicates with **InventoryService** via OpenFeign client
- Services are designed to be loosely coupled and independently deployable

## Technology Stack

- **Spring Boot 3.4.x/3.5.x**
- **Spring Data JPA** (for MySQL services)
- **Spring Data MongoDB** (for ProductAPI)
- **Spring Cloud OpenFeign** (for inter-service communication)
- **Flyway** (for database migrations)
- **Lombok** (for reducing boilerplate code)
- **SpringDoc OpenAPI** (for API documentation)
- **Testcontainers** (for integration testing)
- **Docker** (for containerization)

## Development

### Code Style
- Uses Lombok for reducing boilerplate code
- Follows standard Spring Boot project structure
- Uses OpenAPI 3 for API documentation

### Testing
- Unit tests with JUnit 5
- Integration tests with Testcontainers
- Each service has its own test suite

## License

This project is licensed under the terms specified in the LICENSE file.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Troubleshooting

### Common Issues

1. **Port conflicts**: Ensure ports 8080, 8082, 8083, 3306, 3316, and 27017 are available
2. **Database connection**: Verify Docker containers are running and accessible
3. **Build issues**: Run `mvn clean install` to resolve dependency issues

### Useful Commands

```bash
# Check running Docker containers
docker ps

# View application logs
docker-compose logs

# Stop all services
docker-compose down
```
