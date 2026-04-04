# 📋 Full Stack Web Application — User Data Collection Portal

---

## 🚀 Project Overview

This is a full-stack web application where users can log in and submit their personal details along with a PDF file. The data is stored in a database, and users can download their submitted details as a generated PDF.

Only authenticated users can access the system, and each user can only view/download their own data.

---

## 🛠️ Prerequisites and Dependencies

Make sure the following are installed:

* Java JDK 17 or above
* Maven
* MySQL Server
* Web Browser (Chrome recommended)

Dependencies used in the project:

* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL Driver
* Apache PDFBox

---

## ⚙️ Steps to Set Up and Run the Project Locally

### 1. Clone the Repository

```bash
git clone https://github.com/redinsideout/Full-Stack-Web-Application-User-Data-Collection-Portal.git
cd Full-Stack-Web-Application-User-Data-Collection-Portal
```

---

### 2. Configure Database

Create a MySQL database:

```sql
CREATE DATABASE user_portal;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_portal
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

### 3. Run the Backend

```bash
mvn spring-boot:run
```

---

### 4. Open the Application

Open browser and go to:

```
http://localhost:8080
```

---

## 🗄️ Database Setup Instructions and Seed Command

You can create tables using the provided `schema.sql` file.

Run this in MySQL:

```sql
SOURCE schema.sql;
```

---

## 👤 Demo Login Credentials

Use the following credentials to log in:

```
Email: info@poshatva.com 
Password: Poshatva@1234
```

---

## 📄 SQL Schema

Example schema for required tables:

```sql
CREATE TABLE IF NOT EXISTS users (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS submissions (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    full_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL,
    pdf_path      VARCHAR(512) NOT NULL,
    submitted_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

## 🌱 Seed File / SQL Insert for Demo User

```sql
INSERT IGNORE INTO users (email, password)
VALUES (
    'info@poshatva.com',
    '$2a$10$MAacBoV05zazBDoGfjMn.OUBMwRNxOCA7W3Vg2iK6fsCDUu6wbeD2'
);```

👉 Note: Password is stored in hashed format using BCrypt.

---

## ✨ Features

* 🔐 User Authentication (Login)
* 📝 Form Submission with Validation
* 📁 PDF Upload (Max 5MB)
* 🗄️ Data Stored in MySQL Database
* 📄 Dynamic PDF Generation & Download
* 🔒 Secure Access (User-specific data only)

---

## 📌 Notes

* Uploaded files are stored in `/uploads/` directory
* Only the file path is stored in the database
* Only `.pdf` files are allowed
* Maximum file size: 5MB

---

## 🎯 Conclusion

This project demonstrates full-stack development, including authentication, file handling, database integration, and secure user data management using Spring Boot.

---
