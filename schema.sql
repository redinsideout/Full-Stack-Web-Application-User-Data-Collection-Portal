-- ============================================================
--  IITD User Data Collection Portal — Schema & Seed Data
--  Run this against MySQL: mysql -u root -p iitd_db < schema.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS iitd_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE iitd_db;

-- ── Users Table ──────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS users (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL COMMENT 'BCrypt hashed',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);

-- ── Submissions Table ────────────────────────────────────────
CREATE TABLE IF NOT EXISTS submissions (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    full_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL,
    pdf_path      VARCHAR(512),
    submitted_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT fk_submissions_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ── Seed User ────────────────────────────────────────────────────
-- Email:    info@poshatva.com
-- Password: Poshatva@1234  (BCrypt hash of the plain-text password)
INSERT IGNORE INTO users (email, password)
VALUES (
    'info@poshatva.com',
    '$2a$10$MAacBoV05zazBDoGfjMn.OUBMwRNxOCA7W3Vg2iK6fsCDUu6wbeD2'
);

-- ── Indexes ──────────────────────────────────────────────────
CREATE INDEX IF NOT EXISTS idx_submissions_user_id ON submissions(user_id);
CREATE INDEX IF NOT EXISTS idx_users_email         ON users(email);
