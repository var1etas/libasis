package ru.filatov.libasis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    /**
     * Находит всех пользователей с заданным название роли
     * @param role наименование роли
     */
    @Query("FROM UserEntity WHERE role.role = :role")
    List<UserEntity> findByRole(String role);
}
