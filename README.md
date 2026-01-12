# Technical test documentation

## Getting Started

### Prerequisites

- Java 21
- Maven

### Running Locally

1. Build the application:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Running Locally with Docker

1. Run the application with Docker compose (Docker file includes will build the app):
   ```bash
   docker compose up
   ```

## Testing
For convenience the service uses H2 for local testing & adds some example data.

The project includes E2E and unit tests that can be run with:
   ```bash
   mvn test
   ```
E2E are defined with Cucumber and the specification can be found in `src/test/resources/features`.

Example of request:
   ```bash
   curl --location 'http://localhost:8080/api/prices/1/products/35455?effectiveDate=2020-06-14T10%3A00'
   ```

## Considerations
- Application implemented using hexagonal architecture 
- Designed so that the application and domain layers have no references to external libraries 
- The infrastructure layer uses Spring Boot, as well as an H2 database connection 
- The data model has been kept simple, and no tables have been created for Product or Brand to keep the implementation straightforward 
- The API is documented with Swagger. Once the application is running, it can be accessed at the following URL: http://localhost:8080/swagger-ui/index.html

