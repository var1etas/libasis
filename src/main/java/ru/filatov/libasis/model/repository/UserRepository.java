package ru.filatov.libasis.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с пользователями
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    /**
     * Возвращает пользователя по логину
     */
    Optional<UserEntity> findByLogin(String login);

    /**
     * Возвращает всех пользователей в системе
     */
    @Query("FROM UserEntity all")
    List<UserEntity> getAll();

    /**
     * Возвращает всех пользователей с заданным статусом
     */
    List<UserEntity> getAllByStatus(Boolean status);
}
