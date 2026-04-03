CREATE DATABASE IF NOT EXISTS iitd_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE iitd_db;

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
    pdf_path      VARCHAR(512),
    submitted_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT fk_submissions_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT IGNORE INTO users (email, password)
VALUES (
    'info@poshatva.com',
    '$2a$10$MAacBoV05zazBDoGfjMn.OUBMwRNxOCA7W3Vg2iK6fsCDUu6wbeD2'
);

CREATE INDEX IF NOT EXISTS idx_submissions_user_id ON submissions(user_id);
CREATE INDEX IF NOT EXISTS idx_users_email         ON users(email);
