package ru.filatov.libasis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;

import java.util.List;

@Repository
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
        booksContainer.remove(bookId-1);
    }

    @Override
    public void update(BookEntity book) {
        booksContainer.set(book.getId()-1, book);
    }
}
