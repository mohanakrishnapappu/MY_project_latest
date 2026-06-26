# StructuraX Backend

## Project Overview

StructuraX is a Construction Project Management System designed to manage construction projects, users, project teams, and role-based access securely using Spring Boot and JWT Authentication.

---

## Tech Stack

* Java 21
* Spring Boot 3
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger/OpenAPI

---

## Features Implemented

### Day 1–3: Project Setup

* Spring Boot project creation
* Maven configuration
* MySQL database connection
* Basic application structure

---

### Day 4–5: Project Module

Implemented CRUD operations for Projects.

#### Project Fields

* id
* projectName
* location
* plotArea
* floors
* status

#### APIs

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| GET    | /api/projects      | Get all projects  |
| GET    | /api/projects/{id} | Get project by id |
| POST   | /api/projects      | Create project    |
| PUT    | /api/projects/{id} | Update project    |
| DELETE | /api/projects/{id} | Delete project    |

---

### Day 6–7: Validation & Exception Handling

Added:

* Bean Validation
* Global Exception Handler
* ResourceNotFoundException
* UserAlreadyExistsException

Validation examples:

* @NotBlank
* @NotNull
* @Email
* @Size

---

### Day 8–10: User Management

Implemented User Registration and Login.

#### User Fields

* id
* fullName
* email
* password
* role

#### APIs

| Method | Endpoint            | Description   |
| ------ | ------------------- | ------------- |
| POST   | /api/users/register | Register user |
| POST   | /api/users/login    | Login user    |

---

### Day 11–12: JWT Authentication

Implemented secure authentication using JWT.

#### Features

* JWT token generation
* JWT validation
* Authentication filter
* Stateless session management
* Password encryption using BCrypt

#### Security Components

* JwtUtil
* JwtAuthenticationFilter
* SecurityConfig
* CustomUserDetailsService

#### Protected Endpoint

| Method | Endpoint      |
| ------ | ------------- |
| GET    | /api/users/me |

Returns currently logged-in user information.

---

### Day 13: User Management APIs

Added Admin User Management.

#### APIs

| Method | Endpoint             |
| ------ | -------------------- |
| GET    | /api/users           |
| GET    | /api/users/{id}      |
| PUT    | /api/users/{id}/role |
| DELETE | /api/users/{id}      |

#### Security

Only ADMIN users can access these endpoints.

---

### Day 14: Project Member Management

Implemented Project Team Assignment Module.

#### ProjectMember Entity

Fields:

* id
* project
* user
* role

#### Features

Assign users to projects.

Examples:

* PROJECT_MANAGER
* SITE_ENGINEER
* ARCHITECT
* CONTRACTOR

#### API

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | /api/project-members |

Sample Request

```json
{
  "projectId": 1,
  "userId": 5,
  "role": "PROJECT_MANAGER"
}
```

Sample Response

```json
{
  "id": 1,
  "project": {
    "id": 1,
    "projectName": "StructuraX Tower"
  },
  "user": {
    "id": 5,
    "fullName": "Mohana Krishna"
  },
  "role": "PROJECT_MANAGER"
}
```

---

## Authentication

### Login

Endpoint

```http
POST /api/users/login
```

Request

```json
{
  "email": "mohana@gmail.com",
  "password": "admin123"
}
```

Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

### Using JWT Token

Add token in Swagger Authorize button:

```text
Bearer YOUR_JWT_TOKEN
```

Example:

```text
Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## Database Tables

### users

* id
* full_name
* email
* password
* role

### projects

* id
* project_name
* location
* plot_area
* floors
* status

### project_members

* id
* project_id
* user_id
* role

---

## Current Progress

Completed: Day 14

Modules Completed:

* Project Management
* User Management
* JWT Authentication
* Role-Based Authorization
* Project Team Assignment

---

## Upcoming Features

### Day 15

Material Management Module

Features:

* Add Materials
* Update Stock
* Material Categories
* Inventory Tracking

### Future Modules

* Task Management
* Daily Progress Reports
* Expense Tracking
* File Uploads
* Dashboard Analytics
* Notification System
* Audit Logs

---

## Author

Mohana Krishna

StructuraX Construction Management System
Spring Boot + MySQL + JWT Security

## Day 15 - Task Management Module

### Features Implemented

* Task Entity
* Task Repository
* Task Service
* Task Controller
* Project ↔ Task Relationship
* User ↔ Task Relationship
* JWT Protected Endpoints
* CRUD Operations

### API Endpoints

#### Create Task

POST /api/tasks

#### Get All Tasks

GET /api/tasks

#### Get Task By Id

GET /api/tasks/{id}

#### Update Task

PUT /api/tasks/{id}

#### Delete Task

DELETE /api/tasks/{id}

#### Get Tasks By User

GET /api/tasks/user/{userId}

#### Get Tasks By Project

GET /api/tasks/project/{projectId}

### Task Fields

* Title
* Description
* Priority
* Status
* Progress
* Start Date
* Due Date
* Project
* Assigned User

### Validation

* Project must exist
* Assigned User must exist
* Title is mandatory

### Security

All endpoints protected using JWT Authentication.

### Database Tables

tasks

Columns:

* id
* title
* description
* priority
* status
* progress
* start_date
* due_date
* project_id
* assigned_user_id

### Testing

Successfully tested using Swagger UI:

* Create Task
* Get All Tasks
* Get Task By Id
* Update Task
* Delete Task

Status: Completed

