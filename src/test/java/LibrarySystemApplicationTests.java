
import org.junit.jupiter.api.Test;

import org.librarySystem.model.Admin;
import org.librarySystem.model.Books;
import org.librarySystem.model.BooksBorrowed;
import org.librarySystem.model.Members;
import org.librarySystem.services.AdminService;
import org.librarySystem.services.BooksBorrowedService;
import org.librarySystem.services.BooksService;
import org.librarySystem.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class LibrarySystemApplicationTests {
    @Autowired
    AdminService adminService;
    @Autowired
    BooksBorrowedService booksBorrowedService;
    @Autowired
    BooksService booksService;
    @Autowired
    MembersService membersService;

    @Test
    void savingAdmin() {

        Admin admin = new Admin();
        admin.setName("Tadie");
        admin.setPassword("31130");
        admin.setEmail("tadiehaz@gmail.com");
        admin.setPhoneNumber(773);
        adminService.saveAdmin(admin);
    }

    @Test
    void savingBooksBorrowed() {

        BooksBorrowed booksborrowed = new BooksBorrowed();
        booksborrowed.setBookTitle("RICH BRAINS");
        booksborrowed.setBookCopyID("L3");
        booksborrowed.setEmail("timbo@gmail.com");
      //  booksborrowed.setBookBorrowedDate(new LocalDate ());
      //  booksborrowed.setBookDueDate(new LocalDate());
        booksBorrowedService.saveBooksBorrowed(booksborrowed);
    }

    @Test
    void savingBooks() {

        Books books = new Books();
        books.setBookTitle("Richness");
        books.setBookID("T12");
       // books.setQuantity(3);
        books.setYearPublished(new Date());
        booksService.saveBooks(books);
    }

    @Test
    void savingMembers() {

        Members members = new Members();
        members.setFullName("Muramba");
        members.setAddress("kuwadzana");
        members.setEmail("mura@gmail.com");
        members.setPhoneNumber(33);
        members.setAge(34);
        membersService.saveMembers(members);
    }
}
