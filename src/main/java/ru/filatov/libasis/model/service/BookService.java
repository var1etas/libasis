package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.dto.BookRequestDto;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.entity.GenreEntity;
import ru.filatov.libasis.model.repository.BookRepository;
import ru.filatov.libasis.model.repository.GenreRepository;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BookService {
    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    public void createBook(BookRequestDto bookRequestDto) {
        GenreEntity bookGenre = genreRepository.findByName(bookRequestDto.genre());
        BookEntity book = new BookEntity(bookRequestDto.title(), bookRequestDto.author(),
                bookRequestDto.description(), bookGenre, true);

        bookRepository.save(book);
    }

    public boolean updateBook(Long id, BookRequestDto bookRequestDto) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return false;
        }
        GenreEntity bookGenre = genreRepository.findByName(bookRequestDto.genre());
        book.get().setTitle(bookRequestDto.title());
        book.get().setAuthor(bookRequestDto.author());
        book.get().setDescription(bookRequestDto.description());
        book.get().setGenre(bookGenre);
        bookRepository.save(book.get());
        return true;
    }
    public boolean deleteBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return false;
        }
        bookRepository.delete(book.get());
        return true;
    }
    public BookEntity getBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        return book.get();
    }
}
