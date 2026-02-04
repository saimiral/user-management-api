# User Management REST API

Production-style REST API built with Spring Boot following layered architecture principles.
Supports CRUD operations, pagination, sorting, global exception handling, and DTO-based data transfer.

Tech stack:
Java  
Spring Boot  
Spring Data JPA  
Hibernate  
MySQL  
Maven

Architecture:
Controller → Service → Repository  
DTO pattern for request/response  
Global Exception Handling  
Transactional service layer

Features:
Create, update, delete users  
Pagination & sorting  
Validation  
Exception handling  
Layered architecture

API endpoints:
GET /users → Get paginated users  
POST /users → Create user  
PUT /users/{id} → Update user  
DELETE /users/{id} → Delete user  

Sample Request / Response:
POST /users:

{
  "name": "John",
  "age": 25
}

Response:
{
  "id": 1,
  "name": "John",
  "age": 25
}

Future improvements:
- Docker support
- Unit testing
- Spring Security
