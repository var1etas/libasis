package ru.filatov.libasis.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.filatov.libasis.model.entity.ReserveEntity;

import java.util.List;

/**
 * Репозиторий для работы с резервациями
 */
@RepositoryRestResource
public interface ReserveRepository extends CrudRepository<ReserveEntity, Long> {
    /**
     * Возвращает резервацию по заданной книге, пользователю и статусу возврата
     */
    ReserveEntity findByBookIdAndUserLoginAndIsReturned(Long book_id, String user_login, Boolean isReturned);

    /**
     * Возвращает все резервации заданного пользователя и статуса возврата
     */
    List<ReserveEntity> findAllByUserLoginAndIsReturned(String username, Boolean isReturned);

    /**
     * Возвращает все не закрытые резервации
     */
    @Query("FROM ReserveEntity WHERE isReturned = false")
    List<ReserveEntity> findNotReturned();

    /**
     * Возвращает все резервации с заданным статусом просроченного дедлайна
     */
    List<ReserveEntity> findAllByDeadlineStatus(Boolean deadline_status);

    /**
     * Возвращает все резервации
     */
    @Query("FROM ReserveEntity all")
    List<ReserveEntity> getAll();
}
