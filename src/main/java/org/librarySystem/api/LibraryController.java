package org.librarySystem.api;


import jakarta.servlet.http.HttpSession;
import org.librarySystem.model.Admin;
import org.librarySystem.model.Books;
import org.librarySystem.model.BooksBorrowed;
import org.librarySystem.model.Members;
import org.librarySystem.repository.AdminRepository;
import org.librarySystem.services.AdminService;
import org.librarySystem.services.BooksBorrowedService;
import org.librarySystem.services.BooksService;
import org.librarySystem.services.MembersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
public class LibraryController {
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BooksService bookService;
    private final AdminService adminService;
    private final MembersService membersService;
    private final BooksService booksService;
    private final BooksBorrowedService booksBorrowedService;

    @Autowired
    public LibraryController(AdminService adminService, MembersService membersService, BooksService booksService, BooksBorrowedService booksBorrowedService) {
        this.adminService = adminService;
        this.membersService = membersService;
        this.booksService = booksService;
        this.booksBorrowedService = booksBorrowedService;
    }

    @GetMapping("/adminRegForm")
    public String adminReg(@ModelAttribute Admin admin, Model model) {
         if(adminRepository.findByEmail(admin.getEmail())==null) {
        adminService.saveAdmin(admin);
        model.addAttribute("message", "Admin Created Successfully");
             return showAdminLogin(admin);}
         else {
             model.addAttribute("message", "User already exist! " +admin.getName());
         return adminRegDisplay(admin);}


    }

    @GetMapping("/adminReg")
    public String adminRegDisplay(@ModelAttribute Admin admin) {

        return "adminReg";
    }

    @GetMapping("/adminLoginForm")
    public String adminLogin(String email, String password, Model model, Admin adminIncoming, HttpSession session) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            model.addAttribute("loggedInUser", admin);
            model.addAttribute("message", "Welcome " + admin.getName());
            return "dashboard";
        } else {
            model.addAttribute("message", "Login Unsuccessful!");

            return "redirect:/adminLogin";
        }
    }

    @GetMapping("/adminLogin")
    public String showAdminLogin(Admin adminIncoming) {
        return "adminLogin";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Admin adminIncoming, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("message", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(adminIncoming);
        } else {
            return "dashboard";
        }
    }

    @GetMapping("/add-member")
    public String addMembers(@ModelAttribute Members members, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("message", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (membersService.findMemberByEmail(members.getEmail()) == null ) {

                membersService.saveMembers(members);
                model.addAttribute("message", "Member added successfully");
            } else {
                model.addAttribute("message", "Member already exists");

            }
        }
        return dashboard(model, admin, loggedInUser);
    }

    @GetMapping("/remove-member")
    public String removeMember(@ModelAttribute Members members, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("message", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (membersService.findMemberByEmail(members.getEmail()) != null) {
                membersService.removeMembers(members.getEmail());
                model.addAttribute("message", "Member removed successfully");
                return dashboard(model, admin, loggedInUser);
            } else {
                model.addAttribute("message", "Member doesn't exist");
                return dashboard(model, admin, loggedInUser);
            }
        }
    }

    @GetMapping("/borrow-book")
    public String borrowBook(@ModelAttribute BooksBorrowed booksBorrowed, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("message", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (membersService.findMemberByEmail(booksBorrowed.getEmail()) != null) {
                Books books = booksService.findBookByBookCopyID(booksBorrowed.getBookCopyID());
                if (books.getBookCopyID() != null) {
                    if (books.getStatus().equals("Available") || books.getStatus().equals("Returned")) {
                        booksBorrowedService.removeBooksBorrowed(booksBorrowed.getBookCopyID());
                        booksBorrowedService.saveBooksBorrowed(booksBorrowed);
                        model.addAttribute("message", "Book borrowed successfully");
                        booksService.updateBookStatus(books.getBookCopyID(), "Borrowed");

                        return dashboard(model, admin, loggedInUser);
                    } else {
                        model.addAttribute("message", "Book is unavailable");
                    }
                } else {
                    model.addAttribute("message", "Book does not exist");
                }
            } else {
                model.addAttribute("message", "User does not exist");
            }
            return dashboard(model, admin, loggedInUser);
        }
    }

    @GetMapping("/return-book")
    public String returnBook(@ModelAttribute Books books, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("messages", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (booksService.findBookByBookCopyID(books.getBookCopyID()) != null) {

                model.addAttribute("message", "Book Returned successfully");
                booksService.updateBookStatus(books.getBookCopyID(), "Returned");
                booksBorrowedService.findBookByBookCopyIDAndSave(books.getBookCopyID());
            } else {
                model.addAttribute("message", "Book Copy Doesn't Exist ");
            }
            return dashboard(model, admin, loggedInUser);
        }
    }

    @GetMapping("/add-books")
    public String addBooks(@ModelAttribute Books books, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("messages", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (booksService.findBookByBookCopyID(books.getBookCopyID()) == null) {
                booksService.saveBooks(books);
                model.addAttribute("message", "Book added successfully");
                return dashboard(model, admin, loggedInUser);
            } else {
                model.addAttribute("message", "Book Copy ID Already Exists");
            }

            return dashboard(model, admin, loggedInUser);
        }
    }

    @GetMapping("remove-books")
    public String removeBooks(@ModelAttribute Books books, Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("messages", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            if (booksService.findBookByBookCopyID(books.getBookCopyID()) != null) {

                model.addAttribute("message", "Book Removed successfully");
                booksService.updateBookStatus(books.getBookCopyID(), "Removed");

            } else {
                model.addAttribute("message", "Book Copy Doesn't Exist ");
            }
            return dashboard(model, admin, loggedInUser);
        }
    }

    @GetMapping("/reports")
    public String reports(Model model, Admin admin, @SessionAttribute(name = "loggedInUser", required = false) Admin loggedInUser) {
        if (loggedInUser == null) {
            model.addAttribute("message", "Please Login ");

            // Redirect to login page if user is not logged in
            return showAdminLogin(admin);
        } else {
            model.addAttribute("books", bookService.retrieveBooks());
            model.addAttribute("members", membersService.retrieveMembers());
            model.addAttribute("borrowedBooks", booksBorrowedService.retrieveBooksBorrowed());
            return "reports";
        }
    }
}
