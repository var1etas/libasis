package ru.filatov.libasis.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.dto.BookRequestDto;
import ru.filatov.libasis.model.service.BookService;

@Validated
@RequestMapping("/api/book")
@RestController
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createBook(@Valid BookRequestDto bookRequestDto) {
        bookService.createBook(bookRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @Valid BookRequestDto bookRequestDto) {
        if(!bookService.updateBook(bookId, bookRequestDto)){
            return new ResponseEntity<>("Book to update not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        if(!bookService.deleteBook(bookId)){
            return new ResponseEntity<>("Book to delete not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{bookId}")
    public ResponseEntity<String> getBook(@PathVariable Long bookId) {
        if(bookService.getBook(bookId) == null){
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.getBook(bookId).toString(), HttpStatus.OK);
    }
}
