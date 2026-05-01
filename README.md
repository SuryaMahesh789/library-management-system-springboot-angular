# 📚 KnowledgeHUB – BookSphere
### Secure Library Management System (Spring Boot + Angular)

<p align="center">
  <b>A production-style backend system focused on modern security patterns, RBAC, and clean API architecture.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/SpringBoot-3.x-brightgreen?style=for-the-badge&logo=springboot">
  <img src="https://img.shields.io/badge/Security-JWT%20%2B%20BCrypt-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql">
</p>

---

## 🧠 Overview
KnowledgeHUB is more than just a library app; it is a demonstration of secure backend engineering. This project implements a **Stateless Security Architecture** to manage library operations, ensuring that every request is authenticated and authorized through rigorous industry-standard filters.

### 🏗️ System Architecture


The system follows a strictly decoupled flow:
**User** → **Spring Security** → **JWT Filter** → **REST Controller** → **Service Layer** → **MySQL**

---

## 🔐 Security Design (The "Invisible Shield")

### 1. Authentication Flow (BCrypt + JWT)
We never store passwords in plain text. Using **BCrypt**, passwords are "salted" and hashed before storage.
*   **Login:** User credentials are validated against the hashed DB entry.
*   **Issuance:** On success, a signed **JWT Token** is issued to the client.
*   **Verification:** Subsequent requests carry this token, which is validated by our custom `JwtFilter`.

### 2. Authorization Flow (RBAC)
The system enforces **Role-Based Access Control** to protect sensitive endpoints:
*   **ADMIN:** Full authority to manage inventory and oversee the book request lifecycle.
*   **USER:** Restricted access to view books and initiate borrowing requests.

---

## ⚙️ Core Features

*   **🔐 Bulletproof Security:** JWT-based stateless sessions with BCrypt password hashing.
*   **👥 Role-Based Workflow:** Distinct logic for Admins and Users using `@PreAuthorize`.
*   **📚 Book Management:** Full CRUD operations for library inventory.
*   **🔄 Request Lifecycle:** A complete workflow for users to request books and admins to approve or reject them.

---

🏗️ System Architecture

<img width="1387" height="256" alt="image" src="https://github.com/user-attachments/assets/63547b12-629a-4378-ae4a-aff5a779896e" />

🔐 Authentication Flow (BCrypt + JWT)

<img width="769" height="668" alt="image" src="https://github.com/user-attachments/assets/a02f6b7d-0afc-4077-9b3b-07abb29fa1f8" />


🔑 Authorization Flow (RBAC)

<img width="674" height="729" alt="image" src="https://github.com/user-attachments/assets/4084d128-9bee-4d5e-974a-4777553a9d3f" />





## 📂 Project Structure
```text
src/main/java/com/knowledgehub/
├── config/             # App & Security Configurations
├── controller/         # REST API Endpoints
├── dto/                # Data Transfer Objects
├── entity/             # JPA Database Models
├── repository/         # Spring Data JPA Interfaces
├── security/           # JWT & BCrypt Logic
└── service/            # Core Business Logic
