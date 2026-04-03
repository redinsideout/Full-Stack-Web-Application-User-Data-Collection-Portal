# 📋 Full Stack User Data Collection Portal

## 🚀 Project Overview

This is a full-stack web application where users can log in, submit their personal details along with a PDF file, and download their submitted data as a generated PDF.

The application ensures that only authenticated users can access the system and each user can only view/download their own data.

---

## 🛠️ Tech Stack

### Frontend

- HTML
- CSS
- JavaScript (Fetch API)

### Backend

- Java (Spring Boot)
- Spring Security (Authentication)
- MySQL (Database)
- Apache PDFBox (PDF generation)

---

## ✨ Features

- 🔐 User Login (Authentication)
- 📝 Form Submission (Name, Email, Mobile, PDF Upload)
- 📁 PDF File Upload (Stored on server)
- 🗄️ Data Stored in Database
- 📄 Download Generated PDF with User Details
- 🔒 Secure Access (User can only access their own data)
- 🚪 Logout functionality

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/redinsideout/Full-Stack-Web-Application-User-Data-Collection-Portal.git
cd Full-Stack-Web-Application-User-Data-Collection-Portal
```

---

### 2️⃣ Configure MySQL Database

Create a database:

```sql
CREATE DATABASE user_portal;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_portal
spring.datasource.username=root
spring.datasource.password=your_password
```

---

### 3️⃣ Run the Backend

```bash
mvn spring-boot:run
```

---

### 4️⃣ Open the Application

Open browser and go to:

```
http://localhost:8080
```

---

## 👤 Demo Credentials

```
Email: info@poshatva.com
Password: Poshatva@1234
```

---

## 🗄️ Database Structure

### Users Table

- id
- email
- password (hashed)
- created_at

### Submissions Table

- id
- user_id (foreign key)
- full_name
- email
- mobile_number
- pdf_path
- submitted_at

---

## 📄 PDF Download Feature

- User clicks "Download My Details"
- Backend generates PDF dynamically
- PDF includes:
  - Full Name
  - Email
  - Mobile Number
  - Uploaded PDF path

- File is downloaded in browser

---

## 🔐 Security

- Passwords are hashed using BCrypt
- Authentication handled using Spring Security
- All APIs are protected
- Users can only access their own data

---

## 📌 Notes

- Uploaded PDF files are stored in `/uploads/` folder
- Only file path is stored in database
- Maximum file size: 5MB
- Only `.pdf` files are allowed

---

## 🎯 Conclusion

This project demonstrates full-stack development including authentication, file handling, database integration, and secure data access.

---

