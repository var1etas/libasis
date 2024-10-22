package ru.filatov.libasis.service;

import org.springframework.stereotype.Service;
import ru.filatov.libasis.dto.LibraryDto;
import ru.filatov.libasis.service.crud.BookService;
import ru.filatov.libasis.service.crud.LibraryService;

@Service
public class LibraryLotService {
    LibraryService libraryService;
    BookService bookService;
    public LibraryLotService(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    public void addLot(LibraryDto libraryDto) {
        // TODO
    }
}
