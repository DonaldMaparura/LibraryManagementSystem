CREATE TABLE IF NOT EXISTS admin (
    email VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber INT NOT NULL
    );