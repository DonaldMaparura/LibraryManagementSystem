CREATE TABLE IF NOT EXISTS books_borrowed (
    bookTitle VARCHAR(255),
    email VARCHAR(255) PRIMARY KEY,
    bookCopyID VARCHAR(255) ,
    bookBorrowedDate DATE ,
    bookDueDate DATE,
    returnedDate DATE
    );