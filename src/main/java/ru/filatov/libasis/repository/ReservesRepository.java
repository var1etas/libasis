package ru.filatov.libasis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.ReserveEntity;

@Repository
public interface ReservesRepository extends CrudRepository<ReserveEntity, Integer> {
}
