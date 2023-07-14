# recipe-service

This is Backend for recipe server built in springboot, JWT Token and email service. UI is implemented in react JS

## Features in this application- 

- Jwt support for quick login.
- Regular Username/Password authentication.
- Stores user information in the PostgreSQL database.
- Email verification
- View Recipes
- comment, ratings and view ingredients
- Stores API data in Redis Cache to minimize network calls.

## Tools and Technologies

- Java 11
- Spring Boot
- Spring Web MVC
- Spring Security and JWT token
- Spring Swagger Fox UI 
- Spring Data JPA
- Spring Mail
- Apache Maven
- PostGreSQL Database
- Docker
- CI/CD
- Junit and Mockito for Unit Testing

## High Level Design for the Application - 

![image](https://github.com/coding-nomadic/recipe-service/assets/8009104/94f1fba3-5e7f-492c-a392-d6e7ebd0be4c)


## Application API server is running in Render Cloud with this endpoint where swagger is exposed -

- https://recipe-service-ixhm.onrender.com/swagger-ui/index.html#/
- Token servce repo - https://github.com/coding-nomadic/authorisation-service
- react js UI client repo -https://github.com/coding-nomadic/recipe-sharing-client
- React UI running here - https://recipe-himalayan.netlify.app/
