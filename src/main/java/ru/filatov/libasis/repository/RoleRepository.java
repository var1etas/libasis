package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.RoleEntity;

@Repository
@RepositoryRestResource(path = "roles")
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
}
