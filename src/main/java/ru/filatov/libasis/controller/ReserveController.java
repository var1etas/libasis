package ru.filatov.libasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.entity.ReserveEntity;
import ru.filatov.libasis.model.service.BookService;
import ru.filatov.libasis.model.service.ReserveService;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с резервацией книг
 */
@Controller
@RequestMapping("/api/reserve")
public class ReserveController {
    ReserveService reserveService;
    BookService bookService;

    @Autowired
    public ReserveController(ReserveService reserveService, BookService bookService) {
        this.reserveService = reserveService;
        this.bookService = bookService;
    }

    /**
     * Обработка запросов на получение пользовательских резерваций
     */
    @GetMapping
    public String getReserves(Authentication authentication, Model model) {
        List<ReserveEntity> reserves = reserveService.getReservesByUsername(authentication.getName());
        model.addAttribute("reserves", reserves);
        return "reserves";
    }

    /**
     * Обработка запросов на резервацию книги
     */
    @GetMapping("{bookId}")
    public String reserveBook(@PathVariable Long bookId, Authentication authentication) {
        boolean flag = reserveService.reserveBook(bookId, authentication.getName());
        if (!flag) {
            return "Невозможно забронировать книгу";
        }
        return "redirect:/api/reserve";
    }

    /**
     * Обработка запросов на возврат книги
     */
    @GetMapping("/return/{bookId}")
    public String returnBook(@PathVariable Long bookId, Authentication authentication) {
        boolean flag = reserveService.returnBook(bookId, authentication.getName());
        if (!flag) {
            return "Невозможно вернуть книгу";
        }
        return "redirect:/api/reserve";
    }
}
