package ru.filatov.libasis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.ReportEntity;

@RepositoryRestResource
public interface ReportRepository extends CrudRepository<ReportEntity, Long> {
}
