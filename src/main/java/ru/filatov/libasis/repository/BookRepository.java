package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.BookEntity;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByTitle(String title);
}
