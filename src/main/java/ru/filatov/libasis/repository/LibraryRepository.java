package ru.filatov.libasis.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.LibraryEntity;

@RepositoryRestResource
public interface LibraryRepository extends CrudRepository<LibraryEntity, Integer> {
    @Modifying
    @Query("DELETE FROM LibraryEntity WHERE book.id = :bookId")
    void deleteByBookId(Integer bookId);
}
