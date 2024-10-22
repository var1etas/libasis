package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.RoleEntity;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
}
