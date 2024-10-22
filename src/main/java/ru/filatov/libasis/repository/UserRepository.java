package ru.filatov.libasis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.UserEntity;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("FROM UserEntity WHERE role.name = :role")
    List<UserEntity> findByRole(String role);
}
