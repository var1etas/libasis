package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByTitle(String title);
}
