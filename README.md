Backend REST API for a service marketplace platform built with **Java & Spring Boot**.
The project demonstrates secure authentication, clean architecture, real-world domain modeling, and multipart file handling.

---

## 🔧 Tech Stack

- Java 21
- Spring Boot 4.x
- Spring Security (JWT)
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Swagger (OpenAPI)

---

## 🏗 Architecture

Layered architecture:

Controller → Service → Repository → Entity

Key design principles:
- DTO pattern (no entity exposure)
- Clear separation of concerns
- Stateless authentication (JWT)
- Role-based authorization
- UUID primary keys
- Proper JPA relationship modeling

---

## 🔐 Security

- JWT-based authentication
- Custom `SecurityFilterChain`
- Role-based access control:
  - USER
  - HUSTLER
  - ADMIN
- `@PreAuthorize` endpoint protection

---

## 📦 Core Features

- User registration & login
- Hustler profile
- CRUD for Offers
- Multipart image upload (`MultipartFile`)
- Search & filtering
- Swagger API documentation

---

## 🗃 Database Modeling

- OneToOne: User ↔ HustlerProfile
- OneToMany: HustlerProfile → Offers
- OneToMany: Offer → Images
