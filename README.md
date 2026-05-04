# 🔐 Simple Auth API (Spring Boot + JWT)

A simple authentication and authorization system built using **Spring Boot**, **Spring Security**, **JWT**, and **MySQL**.  
This project demonstrates user registration, login, role-based access, and protected APIs.

---

## 🚀 Features

- User Registration
- User Login with JWT Token
- Password Encryption using BCrypt
- Role-based Authorization (USER / ADMIN)
- Protected REST APIs
- JWT Authentication Filter
- Spring Security Integration

---

## 🛠️ Tech Stack

- Java 25
- Spring Boot 4.x
- Spring Security
- Spring Data JPA
- MySQL
- JSON Web Token (JWT)
- Lombok

---

---

## 🔐 Authentication Flow

1. User registers at `/auth/register`
2. User logs in at `/auth/login`
3. JWT token is generated
4. Token is sent in header:
   ```
   Authorization: Bearer <token>
   ```
5. Protected APIs validate token via `JwtFilter`

---

## 📡 API Endpoints

### 🔓 Auth APIs

#### Register User
```http
POST /auth/register
## 📁 Project Structure
