package ru.filatov.libasis.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import ru.filatov.libasis.entity.LibraryEntity;

import java.util.List;

public interface LibraryRepositoryCustom {
    /**
     * Находит всех пользователей с заданным именем
     * @param rate имя пользователя
     */
    List<LibraryEntity> findByRate(Float rate);
    /**
     * Находит всех пользователей с заданным название роли
     * @param author наименование роли
     */
    List<LibraryEntity> findByAuthor(String author);
}
