package ru.filatov.libasis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    /**
     * Находит всех пользователей с заданным именем
     * @param title имя пользователя
     */
    List<BookEntity> findByTitle(String title);

}
