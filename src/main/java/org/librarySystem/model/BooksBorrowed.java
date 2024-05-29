package org.librarySystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class BooksBorrowed {
    String bookTitle;
    String bookCopyID;
    @Id
    String email;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate bookBorrowedDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate  bookDueDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate  returnedDate;
    public LocalDate getReturnDate() {
        return returnedDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnedDate = returnDate;
    }



    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookCopyID() {
        return bookCopyID;
    }

    public void setBookCopyID(String bookCopyID) {
        this.bookCopyID = bookCopyID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate  getBookBorrowedDate() {
        return bookBorrowedDate;
    }

    public void setBookBorrowedDate(LocalDate  bookBorrowedDate) {
        this.bookBorrowedDate = bookBorrowedDate;
    }

    public LocalDate  getBookDueDate() {
        return bookDueDate;
    }

    public void setBookDueDate(LocalDate  bookDueDate) {
        this.bookDueDate = bookDueDate;
    }

}
