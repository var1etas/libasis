package ru.filatov.libasis.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.BookEntity;

import java.util.List;

/**
 * Репозиторий для работы с книгами
 */
@RepositoryRestResource
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    /**
     * Возвращает все книги по заданному названию
     */
    List<BookEntity> findByTitle(String title);

    /**
     * Возвращает все книги по заданному автору
     */
    List<BookEntity> findByAuthor(String author);

    /**
     * Возвращает все книги по заданному жанру
     */
    List<BookEntity> findByGenre(String genre);

    /**
     * Возвращает все книги
     */
    @Query("FROM BookEntity all")
    List<BookEntity> getAll();
}
