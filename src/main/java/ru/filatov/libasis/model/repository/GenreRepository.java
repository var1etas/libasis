package ru.filatov.libasis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.GenreEntity;

@RepositoryRestResource
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
    GenreEntity findByName(@Param("name") String name);
}
