package ru.filatov.libasis.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.ReportEntity;

import java.util.List;

/**
 * Репозиторий для работы с отчетами
 */
@RepositoryRestResource
public interface ReportRepository extends CrudRepository<ReportEntity, Long> {
    /**
     * Возвращает все отчеты
     */
    @Query("FROM ReportEntity all")
    List<ReportEntity> getAll();
}
