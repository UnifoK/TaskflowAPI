# Taskflow API

A secure, robust REST API for a task management application built with Java and the Spring Boot framework. This project provides a complete backend solution for user authentication and task management, following modern API design principles.

-----

## \#\# Features

* **Secure Authentication & Authorization:** Full user registration and login functionality using Spring Security and JSON Web Tokens (JWT). Passwords are securely hashed using BCrypt.
* **Token-Based Security:** Endpoints are protected, and users can only access and manage their own tasks.
* **CRUD Functionality:** Complete Create, Read, Update, and Delete (CRUD) operations for tasks.
* **Relational Data Model:** A one-to-many relationship between users and their assigned tasks, managed by Spring Data JPA.
* **Global Exception Handling:** Clean, consistent JSON error responses for API exceptions.

-----

## \#\# Tech Stack

* **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA
* **Database:** MySQL
* **Build Tool:** Maven
* **Dependencies:** `jjwt` for token management, `Lombok` for reducing boilerplate code.

-----

## \#\# API Endpoints

Below is the documentation for the available API endpoints.

### User Management

* **Register a New User**

    * **Method:** `POST`
    * **URL:** `/api/users/register`
    * **Description:** Creates a new user account.
    * **Request Body:**
      ```json
      {
          "username": "user",
          "password": "password",
          "email": "user@example.com"
      }
      ```

* **Authenticate and Login**

    * **Method:** `POST`
    * **URL:** `/api/users/login`
    * **Description:** Authenticates a user and returns a JWT if credentials are valid.
    * **Request Body:**
      ```json
      {
          "username": "user",
          "password": "password"
      }
      ```
    * **Response Body:**
      ```json
      {
          "token": "eyJhbGciOiJIUzI1NiJ9..."
      }
      ```

### Task Management

*Requires a valid JWT in the `Authorization: Bearer <token>` header.*

* **Create a New Task for a User**

    * **Method:** `POST`
    * **URL:** `/api/users/{userId}/tasks`
    * **Description:** Creates a new task and assigns it to the specified user. The logged-in user must match the `userId`.
    * **Request Body:**
      ```json
      {
          "title": "My First Task",
          "description": "Complete the project README",
          "completed": false
      }
      ```

* **Get All Tasks for a User**

    * **Method:** `GET`
    * **URL:** `/api/users/{userId}/tasks`
    * **Description:** Retrieves a list of all tasks belonging to the specified user. The logged-in user must match the `userId`.

* **Update a Task**

    * **Method:** `PUT`
    * **URL:** `/api/tasks/{taskId}`
    * **Description:** Updates the details of a specific task. The logged-in user must be the owner of the task.
    * **Request Body:**
      ```json
      {
          "title": "Updated Task Title",
          "description": "Updated description.",
          "completed": true
      }
      ```

* **Delete a Task**

    * **Method:** `DELETE`
    * **URL:** `/api/tasks/{taskId}`
    * **Description:** Deletes a specific task. The logged-in user must be the owner of the task.