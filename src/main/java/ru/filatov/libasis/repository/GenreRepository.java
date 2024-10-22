package ru.filatov.libasis.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.GenreEntity;

@RepositoryRestResource
public interface GenreRepository extends CrudRepository<GenreEntity, Integer> {
}
