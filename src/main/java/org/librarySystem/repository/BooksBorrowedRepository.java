package org.librarySystem.repository;


import org.librarySystem.model.BooksBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksBorrowedRepository extends JpaRepository<BooksBorrowed,String> {
    BooksBorrowed findByBookCopyID(String bookCopyID);
}
