package org.librarySystem.repository;


import org.librarySystem.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,String> {
    Books findByBookID(String bookID);
    Books findByBookCopyID(String bookCopyID);

}
