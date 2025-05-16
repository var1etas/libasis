package ru.filatov.libasis.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookEntity> createBook(BookEntity book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<BookEntity> updateBook(BookEntity book, @PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.updateBook(book, bookId), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}
