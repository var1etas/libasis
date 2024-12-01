package ru.filatov.libasis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.BookEntity;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findByTitle(String title);
}
