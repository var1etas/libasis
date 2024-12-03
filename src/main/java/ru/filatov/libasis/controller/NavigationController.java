package ru.filatov.libasis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для блока навигации
 */
@RequestMapping("/")
@Controller
public class NavigationController {
    /**
     * Возвращает блок навигации
     */
    @GetMapping
    public String navigate() {
        return "navigator";
    }
}
