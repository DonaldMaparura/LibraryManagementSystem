package org.librarySystem.services;


import org.librarySystem.model.Books;
import org.librarySystem.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void saveBooks(Books booksIncoming) {
        Books books = new Books();
        books.setBookTitle(booksIncoming.getBookTitle());
        books.setBookID(booksIncoming.getBookID());
        books.setBookCopyID(booksIncoming.getBookCopyID());
        books.setYearPublished(booksIncoming.getYearPublished());
        books.setStatus("Available");
        booksRepository.save(books);
    }

    public List<Books> retrieveBooks() {

         return booksRepository.findAll();
    }
    public Books findBookByID(String bookID) {
        return booksRepository.findByBookID(bookID);
    }
    public void updateBookStatus(String bookCopyID, String status){
        Books book = booksRepository.findByBookCopyID(bookCopyID);;
        if (book != null) {
            // Update the status of the book
            book.setStatus(status);
            booksRepository.save(book);
        }
    }
    public Books findBookByBookCopyID(String bookCopyID) {
        return booksRepository.findByBookCopyID(bookCopyID);
    }

}