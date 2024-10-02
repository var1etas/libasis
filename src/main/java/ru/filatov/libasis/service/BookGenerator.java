package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.repository.BookRepository;

@Component
public class BookGenerator {
    BookRepository bookRepository;

    @Autowired
    public BookGenerator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void generate() {
        BookEntity book = new BookEntity();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("Joanne Rowling");
        book.setDescription("Best fantasy book");
        book.setInStock(true);
        bookRepository.create(book);
        book = new BookEntity();
        book.setId(2);
        book.setTitle("The brothers Karamazov");
        book.setAuthor("Fyodor Dostoyevsky");
        book.setDescription("Best classic book");
        book.setInStock(true);
        bookRepository.create(book);
        book = new BookEntity();
        book.setId(3);
        book.setTitle("The Picture of Dorian Gray");
        book.setAuthor("Oscar Wilde");
        book.setDescription("Most popular book");
        book.setInStock(true);
        bookRepository.create(book);
        book = new BookEntity();
        book.setId(4);
        book.setTitle("David Copperfield");
        book.setAuthor("Charles Dickens");
        book.setDescription("The story of David Copperfield and his adventures from childhood to maturity is partially an autobiographical novel based on the author’s life experiences");
        book.setInStock(true);
        bookRepository.create(book);
        book = new BookEntity();
        book.setId(5);
        book.setTitle("The Godfather");
        book.setAuthor("Mario Puzo");
        book.setDescription("A powerful story of tradition, blood, honour and of course, family allegiance");
        book.setInStock(true);
        bookRepository.create(book);
        System.out.println("Five test books generated");
    }
}
