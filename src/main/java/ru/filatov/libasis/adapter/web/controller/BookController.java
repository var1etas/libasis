package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookEntity> findByTitle(@RequestParam String title) {
        return bookRepository.findByTitle(title);
    }
}
