package ru.filatov.libasis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("FROM UserEntity WHERE role.role = :role")
    List<UserEntity> findByRole(String role);
}
