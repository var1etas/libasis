package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.repository.BookRepository;

import java.util.List;


@Service
public class BookService implements EntityService<BookEntity> {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookEntity> findByTitle(String title){
        return repository.findByTitle(title);
    }

    @Override
    public void create(BookEntity book) {
        repository.save(book);
    }

    @Override
    public BookEntity read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, BookEntity book) {
        repository.save(new BookEntity(id, book.getTitle(), book.getAuthor(),
                book.getDescription(), book.getStatus()));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
