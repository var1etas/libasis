package ru.filatov.libasis.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.UserEntity;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("FROM UserEntity WHERE role = :role")
    List<UserEntity> findByRole(String role);

    @Query("FROM UserEntity WHERE login = :login")
    UserEntity findByLogin(String login);
}
