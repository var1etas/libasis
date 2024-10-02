package ru.filatov.libasis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.filatov.libasis.entity.BookEntity;

import java.util.List;
@Component
public class BookRepository implements CrudRepository<BookEntity, Integer> {
    private final List<BookEntity> booksContainer;

    @Autowired
    public BookRepository(List<BookEntity> booksContainer) {
        this.booksContainer = booksContainer;
    }

    @Override
    public void create(BookEntity book) {
        booksContainer.add(book);
    }

    @Override
    public BookEntity read(Integer bookId) {
        return booksContainer.get(bookId-1);
    }

    @Override
    public void delete(Integer bookId) {
        if (booksContainer.get(bookId-1) != null) {
            booksContainer.remove(bookId-1);
        } else {
            System.out.println("Book not found");
        }
    }

    @Override
    public void update(BookEntity book) {
        booksContainer.set(book.getId()-1, book);
    }
}
