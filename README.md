# TransitAssist – Accessible Transit Assistance Booking

## Project Description

TransitAssist is a simple Spring Boot REST API application for managing
accessible transit assistance requests.

The system allows users to create assistance requests and helpers to be
assigned to those requests.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Jakarta Validation
- Postman

## Main Features

1. Create and view users
2. Create and view helpers
3. Create assistance requests
4. Assign helpers to requests
5. Update request status
6. Cancel assistance requests
7. Check helper workload
8. Prevent overlapping helper assignments
9. Validate required input fields
10. Handle application errors using a global exception handler

## Request Status

The application supports:

- REQUESTED
- ASSIGNED
- COMPLETED
- CANCELLED

## Main API Endpoints

### Users

POST `/api/users`

GET `/api/users`

GET `/api/users/{id}`

### Helpers

POST `/api/helpers`

GET `/api/helpers`

GET `/api/helpers/{id}`

GET `/api/helpers/{id}/requests`

### Assistance Requests

POST `/api/requests/user/{userId}`

GET `/api/requests`

GET `/api/requests/{id}`

PUT `/api/requests/{requestId}/assign/{helperId}`

PUT `/api/requests/{requestId}/status?status=STATUS`

DELETE `/api/requests/{id}`

## Database

Database name:

transitassist

The application uses MySQL and Spring Data JPA.

## How to Run

1. Create the MySQL database named `transitassist`.
2. Configure the MySQL username and password in `application.properties`.
3. Run `TransitassistApplication.java`.
4. The application runs on port 8080.
5. Use Postman to test the REST APIs.

## Project Structure

The project follows a simple layered structure:

- Entity – database entities
- Repository – database operations
- Service – business logic
- Controller – REST API endpoints
- Exception – error handling

## Conclusion

TransitAssist demonstrates a beginner-level Spring Boot REST API
application with MySQL database integration and basic business rules
for accessible transit assistance booking.