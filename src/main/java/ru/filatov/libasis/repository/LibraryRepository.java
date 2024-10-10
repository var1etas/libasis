package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.filatov.libasis.entity.LibraryEntity;

public interface LibraryRepository extends CrudRepository<LibraryEntity, Integer> {
}
