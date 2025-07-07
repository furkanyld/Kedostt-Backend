# Kedostt - Backend

**Kedostt** is a web platform designed to help stray animals find loving homes and make it easier for people to donate and apply for adoption. This repository contains the backend services built with **Spring Boot**.

> ✅ **Live API:** [https://api.kedostt.com.tr](https://api.kedostt.com.tr) 
> This backend is currently deployed and actively running on **Render**.

## 🚀 Features

- Spring Boot + Spring Security (JWT-based)
- Role-based authentication: **admin** and **user** login is available
- Public endpoints for donations and adoption applications (no login required)
- Full CRUD operations for animals
- Base64 image upload support
- CORS configuration included

---

### 📁 Project Structure

```text
src/
├── controller
├── service
├── repository
├── dto
├── model
├── security
└── config
```

---

## 📬 Example Endpoints

- GET /api/animals

- POST /api/adoptions

- POST /api/donations

- POST /api/forum

- POST /api/auth/login

---

## ⚙️ Setup

### Requirements
- Java 17+
- Maven
- PostgreSQL or H2 database

---

### Configuration

In your `application.properties` or `application.yml` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/kedostt
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=YourVerySecretKey
jwt.expiration=86400000
```
---

👨‍💻 Developer
LinkedIn: [Furkan Yıldız](www.linkedin.com/in/furkan-yıldız-584383254)
