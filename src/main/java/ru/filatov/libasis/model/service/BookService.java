package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы с книгами
 */
@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    /**
     * Создание книги в системе
     */
    public BookEntity createBook(BookEntity book) {
        book.setStatus(true);
        return bookRepository.save(book);
    }

    /**
     * Обновление книги в системе
     */
    public BookEntity updateBook(BookEntity updatedBook, Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        bookEntity.setTitle(updatedBook.getTitle());
        bookEntity.setAuthor(updatedBook.getAuthor());
        bookEntity.setDescription(updatedBook.getDescription());
        bookEntity.setGenre(updatedBook.getGenre());
        return bookRepository.save(bookEntity);
    }

    /**
     * Удаление книги из системы
     */
    public boolean deleteBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return false;
        }
        bookRepository.delete(book.get());
        return true;
    }

    /**
     * Получение книги по id
     */
    public BookEntity getBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NoSuchElementException("Book not found");
        }
        return book.get();
    }

    /**
     * Получение всех книг в системе
     */
    public List<BookEntity> getAllBooks() {
        return bookRepository.getAll();
    }

    /**
     * Получение всех книг с заданным жанром
     */
    public List<BookEntity> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Получение всех книг с заданным названием
     */
    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    /**
     * Получение всех книг с заданным автором
     */
    public List<BookEntity> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
