package org.librarySystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Books {
    String bookTitle;

    String bookID;
    @Id
    String bookCopyID;
    @DateTimeFormat(pattern = "yyyy-mm-dd")

    Date yearPublished;

    String status;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookCopyID() {
        return bookCopyID;
    }

    public void setBookCopyID(String quantity) {
        this.bookCopyID = quantity;
    }

    public Date getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Date yearPublished) {
        this.yearPublished = yearPublished;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
