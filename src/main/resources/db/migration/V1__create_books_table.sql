CREATE TABLE IF NOT EXISTS books (
    bookTitle VARCHAR(255),
    bookID VARCHAR(255),
    bookCopyID VARCHAR(255) PRIMARY KEY,
    yearPublished DATE,
    status VARCHAR(255)
    );