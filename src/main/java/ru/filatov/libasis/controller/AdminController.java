package ru.filatov.libasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.entity.ReportEntity;
import ru.filatov.libasis.model.entity.ReserveEntity;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.service.BookService;
import ru.filatov.libasis.model.service.ReportService;
import ru.filatov.libasis.model.service.ReserveService;
import ru.filatov.libasis.model.service.UserService;

import java.util.Collections;
import java.util.List;

/**
 * Контроллер для обработки запросов связанных с админ-панелью
 */
@Controller
@RequestMapping("/api/adminpanel")
public class AdminController {

    private UserService userService;
    private BookService bookService;
    private ReportService reportService;
    private ReserveService reserveService;

    @Autowired
    public AdminController(UserService userService, BookService bookService, ReportService reportService, ReserveService reserveService) {
        this.userService = userService;
        this.bookService = bookService;
        this.reportService = reportService;
        this.reserveService = reserveService;
    }

    /**
     * Возвращает навигацию админ-панели
     */
    @GetMapping
    public String getAdminPanel() {
        return "adminPanel";
    }

    /**
     * Обработка запроса на просмотр всех пользователей в системе
     */
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Обработка запроса на деактивацию аккаунта пользователя
     */
    @PostMapping("/users/status/{userId}")
    public String updateUserStatus(@PathVariable Long userId) {
        userService.changeUserStatus(userId);
        return "redirect:/api/adminpanel/users";
    }

    /**
     * Обработка запроса на смену роли пользователя в системе
     */
    @PostMapping("/users/role/{userId}")
    public String updateUserRole(@PathVariable Long userId) {
        userService.changeUserRole(userId);
        return "redirect:/api/adminpanel/users";
    }

    /**
     * Обработка запроса на получение пользователя по логину
     */
    @GetMapping("/user")
    public String getUser(Model model, @RequestParam String login) {
        UserEntity user = userService.getUserByLogin(login).get();
        model.addAttribute("users", user);
        return "users";
    }

    /**
     * Обработка запроса на просмотр всех книг в системе
     */
    @GetMapping("/books")
    public String getBooks(Model model) {
        List<BookEntity> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "booksAdmin";
    }

    /**
     * Возвращает форму создания книги
     */
    @GetMapping("/book")
    public String createBook(Model model) {
        model.addAttribute("book", new BookEntity());
        return "addBook";
    }

    /**
     * Обрабатывает запросы на поиск книг
     */
    @GetMapping("/search")
    public String adminSearch(Model model, @RequestParam String query, @RequestParam String type) {
        List<BookEntity> books;
        switch (type) {
            case "title":
                books = bookService.getBooksByTitle(query);
                break;
            case "author":
                books = bookService.getBooksByAuthor(query);
                break;
            case "genre":
                books = bookService.getBooksByGenre(query);
                break;
            default:
                books = Collections.emptyList();
        }
        model.addAttribute("books", books);
        return "booksAdmin";
    }

    /**
     * Обработка запроса на создание книги
     */
    @PostMapping("/book")
    public String createBook(@ModelAttribute BookEntity book, Model model) {
        try{
            bookService.createBook(book);
            return "redirect:/api/adminpanel/books";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Cannot create book");
            return "addBook";
        }
    }

    /**
     * Возвращает форму редактирования книги
     */
    @GetMapping("/book/{bookId}")
    public String editBook(Model model, @PathVariable Long bookId) {
        model.addAttribute("book", new BookEntity(bookId));
        return "editBook";
    }

    /**
     * Обработка запроса на создание книги
     */
    @PostMapping("/book/{bookId}")
    public String editBook(@ModelAttribute BookEntity book, @PathVariable Long bookId, Model model) {
        try{
            bookService.updateBook(book, bookId);
            return "redirect:/api/adminpanel/books";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Genre not found");
            return "addBook";
        }
    }

    /**
     * Обработка запроса на удаление книги
     */
    @PostMapping("/book/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Model model) {
        bookService.deleteBook(bookId);
        return "redirect:/api/adminpanel/books";
    }

    /**
     * Обработка запроса на создание отчета
     */
    @PostMapping("report")
    public String createReport(Model model) {
        reportService.createReport();
        return "redirect:/api/adminpanel/reports";
    }

    /**
     * Обработка запроса на получение всех отчетов
     */
    @GetMapping("/reports")
    public String getReports(Model model) {
        List<ReportEntity> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "reports";
    }

    /**
     * Обработка запроса на получение всех резерваций
     */
    @GetMapping("/reserves")
    public String getReserves(Model model) {
        List<ReserveEntity> reserves = reserveService.getAllReserves();
        model.addAttribute("reserves", reserves);
        return "adminReserves";
    }
}
