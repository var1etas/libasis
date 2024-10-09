package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.repository.BookRepository;

import java.util.Scanner;
import java.util.regex.Pattern;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    Scanner sc;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        sc = new Scanner(System.in);
        sc.useDelimiter(Pattern.compile("(\\n)|;"));
    }

    @Override
    public void createBook(Integer id, String title, String author, String description) {
        BookEntity book = new BookEntity(id, title, author, description, true);
        bookRepository.create(book);
    }

    @Override
    public void findById(Integer id) {
        System.out.println(bookRepository.read(id));
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public void updateDescription(Integer id, String newDescription) {
        BookEntity book = bookRepository.read(id);
        book.setDescription(newDescription);
        bookRepository.update(book);
    }

    @Override
    public void updateAuthor(Integer id,  String newAuthor) {
        BookEntity book = bookRepository.read(id);
        book.setAuthor(newAuthor);
        bookRepository.update(book);
    }

    @Override
    public void updateTitle(Integer id,  String newTitle) {
        BookEntity book = bookRepository.read(id);
        book.setTitle(newTitle);
        bookRepository.update(book);
    }

    @Override
    public void updateInStockStatus(Integer id, Boolean newStatus) {
        BookEntity book = bookRepository.read(id);
        book.setInStock(newStatus);
        bookRepository.update(book);
    }
}
