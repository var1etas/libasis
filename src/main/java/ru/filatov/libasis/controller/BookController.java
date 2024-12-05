package ru.filatov.libasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.service.BookService;

import java.util.Collections;
import java.util.List;

/**
 * Контроллер для обработки запросов просмотр и поиск книг в системе
 */
@Controller
@RequestMapping("/api")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Обрабатывает запросы на получение всех книг в системе
     */
    @GetMapping("/books")
    public String getBooks(Model model) {
        List<BookEntity> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * Обрабатывает запросы на поиск книг
     */
    @GetMapping("/search")
    public String search(Model model, @RequestParam String query, @RequestParam String type) {
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
        return "books";
    }
}
