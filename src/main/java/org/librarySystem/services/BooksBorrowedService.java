package org.librarySystem.services;


import org.librarySystem.model.BooksBorrowed;
import org.librarySystem.repository.BooksBorrowedRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BooksBorrowedService {
    private final BooksBorrowedRepository booksborrowedRepository;

    public BooksBorrowedService(BooksBorrowedRepository booksborrowedRepository) {
        this.booksborrowedRepository = booksborrowedRepository;
    }
    public void saveBooksBorrowed(BooksBorrowed booksBorrowedIncoming) {
        LocalDate currentDate = LocalDate.now();
        // Convert LocalDate to LocalDateTime at the start of the day
        LocalDateTime startOfDayDateTime = currentDate.atStartOfDay();
        // Convert LocalDateTime back to LocalDate
        LocalDate startDate = startOfDayDateTime.toLocalDate();

        BooksBorrowed booksborrowed = new BooksBorrowed();
        booksborrowed.setBookTitle(booksBorrowedIncoming.getBookTitle());
        booksborrowed.setBookCopyID(booksBorrowedIncoming.getBookCopyID());
        booksborrowed.setEmail(booksBorrowedIncoming.getEmail());
        booksborrowed.setBookBorrowedDate(booksBorrowedIncoming.getBookBorrowedDate());
        booksborrowed.setBookDueDate(booksBorrowedIncoming.getBookDueDate());
        booksborrowed.setReturnDate( LocalDate.of(0,1,1));
        booksborrowedRepository.save(booksborrowed);
    }
    public List<BooksBorrowed> retrieveBooksBorrowed(){
      return booksborrowedRepository.findAll();
    }
    public void findBookByBookCopyIDAndSave(String bookCopyID) {

        BooksBorrowed booksBorrowed= booksborrowedRepository.findByBookCopyID(bookCopyID);
        if (booksBorrowed!=null){
           booksBorrowed.setReturnDate(LocalDate.now());
            booksborrowedRepository.save(booksBorrowed);
        }
    }
    public void removeBooksBorrowed(String bookCopyID){
        BooksBorrowed booksBorrowed = booksborrowedRepository.findByBookCopyID(bookCopyID);

        if (booksBorrowed != null) {
            // Remove the member from the database
            booksborrowedRepository.delete(booksBorrowed);
        }}
}
