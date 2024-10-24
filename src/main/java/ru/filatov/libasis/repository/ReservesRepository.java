package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.entity.ReserveEntity;

@RepositoryRestResource
public interface ReservesRepository extends CrudRepository<ReserveEntity, Integer> {
}
