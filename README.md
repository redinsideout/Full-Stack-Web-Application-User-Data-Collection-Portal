# IITD User Data Collection Portal

A full-stack web application that allows authenticated users to submit personal information and PDF documents, with secure on-the-fly PDF report generation.

---

## 🛠 Tech Stack

| Layer    | Technology              |
|----------|-------------------------|
| Backend  | Java 17 + Spring Boot 3 |
| Frontend | HTML5, CSS3, Vanilla JS |
| Database | MySQL 8+                |
| Security | Spring Security (BCrypt)|
| PDF Gen  | Apache PDFBox 3         |

---

## ⚡ Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/iitd-data-portal.git
cd iitd-data-portal
```

### 2. Create the Database

Run the included SQL schema file:

```bash
mysql -u root -p < schema.sql
```

This will:
- Create the `iitd_db` database
- Create `users` and `submissions` tables
- Insert the seed user with hashed password

### 3. Configure the Application

Edit `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/iitd_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the Application

```bash
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
& "C:\Program Files\apache-maven-3.9.14\bin\mvn.cmd" spring-boot:run
```

The app will start at **http://localhost:8080**

> **Note:** The seed user is automatically created via `DataSeeder.java` on every startup if not already present.

---

## 🔐 Login Credentials

| Field    | Value                |
|----------|----------------------|
| Email    | info@poshatva.com    |
| Password | Poshatva@1234        |

---

## 📁 Project Structure

```
iitd-data-portal/
├── pom.xml
├── schema.sql
├── README.md
├── uploads/                          ← PDF uploads stored here (auto-created)
└── src/
    └── main/
        ├── java/com/iitd/app/
        │   ├── UserDataPortalApplication.java
        │   ├── config/
        │   │   └── DataSeeder.java
        │   ├── controller/
        │   │   ├── AuthController.java
        │   │   └── SubmissionController.java
        │   ├── dto/
        │   │   ├── LoginRequest.java
        │   │   └── SubmissionRequest.java
        │   ├── entity/
        │   │   ├── User.java
        │   │   └── Submission.java
        │   ├── repository/
        │   │   ├── UserRepository.java
        │   │   └── SubmissionRepository.java
        │   ├── security/
        │   │   ├── SecurityConfig.java
        │   │   └── CustomUserDetailsService.java
        │   └── service/
        │       └── SubmissionService.java
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html         ← Login page
                ├── form.html          ← Data collection form
                ├── success.html       ← Confirmation + PDF download
                ├── css/
                │   └── style.css      ← Clean light-mode design system
                └── js/
                    └── app.js         ← Shared JS utilities
```

---

## 🌐 API Endpoints

| Method | Endpoint                  | Auth Required | Description                          |
|--------|---------------------------|:-------------:|--------------------------------------|
| POST   | `/api/auth/login`         | ❌            | Login with email + password           |
| POST   | `/api/auth/logout`        | ✅            | Logout current session                |
| GET    | `/api/auth/status`        | ❌            | Check current authentication status   |
| POST   | `/api/submissions`        | ✅            | Submit form + upload PDF              |
| GET    | `/api/user/download-pdf`  | ✅            | Download generated PDF report         |

---

## 📊 Database Schema

### `users`
| Column     | Type         | Notes            |
|------------|--------------|------------------|
| id         | BIGINT PK    | Auto-increment   |
| email      | VARCHAR(255) | Unique, NOT NULL |
| password   | VARCHAR(255) | BCrypt hashed    |
| created_at | DATETIME     | Auto timestamp   |

### `submissions`
| Column        | Type         | Notes                       |
|---------------|--------------|-----------------------------|
| id            | BIGINT PK    | Auto-increment              |
| user_id       | BIGINT FK    | References `users.id`       |
| full_name     | VARCHAR(255) | NOT NULL                    |
| email         | VARCHAR(255) | NOT NULL                    |
| mobile_number | VARCHAR(20)  | NOT NULL                    |
| pdf_path      | VARCHAR(512) | Filesystem path to PDF file |
| submitted_at  | DATETIME     | Auto timestamp              |

---

## 🖥 UI Pages

| Page           | URL             | Description                                      |
|----------------|-----------------|--------------------------------------------------|
| Login          | `/`             | Sign in with email and password                  |
| Data Form      | `/form.html`    | Submit full name, email, mobile, and PDF upload  |
| Success        | `/success.html` | Confirmation page with PDF download button       |

### UI Design
- Clean, simple **light-mode** design (white cards, light gray background)
- Responsive layout — works on desktop and mobile
- Inline validation on all form fields (client-side + server-side)
- Auth guard on form and success pages — redirects to login if not authenticated
- Navbar shows logged-in email with active status indicator

---

## 🔒 Security

- Passwords are **BCrypt hashed** — never stored in plain text
- Session-based authentication using Spring Security
- All data submission and PDF download routes are protected — require login
- PDF generation is scoped to the authenticated user only — no cross-user data access
- CSRF protection disabled for REST API use

---

## 📎 PDF Download Feature

After submitting the form, the user can click **"Download PDF"** on the success page.

- The frontend sends a GET request to `/api/user/download-pdf`
- The backend verifies the session and fetches **only that user's** submission
- A PDF report is generated on-the-fly using **Apache PDFBox**
- The PDF is returned as a file download (`Content-Disposition: attachment`)

---

## 📝 Notes

- Uploaded PDFs are stored in `./uploads/` on the server filesystem
- The `pdf_path` column stores the path to the uploaded file (not binary content)
- A unique filename (UUID prefix) is used for every uploaded file to prevent collisions
- The seed user is auto-inserted on startup if not already in the database

---

## 📃 License

MIT — Built for IITD Full-Stack Technical Assignment.
