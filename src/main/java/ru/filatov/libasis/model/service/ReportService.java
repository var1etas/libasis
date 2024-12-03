package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.entity.BookEntity;
import ru.filatov.libasis.model.entity.ReportEntity;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.repository.BookRepository;
import ru.filatov.libasis.model.repository.ReportRepository;
import ru.filatov.libasis.model.repository.ReserveRepository;
import ru.filatov.libasis.model.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с отчетами
 */
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReserveRepository reserveRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository,
                         BookRepository bookRepository, ReserveRepository reserveRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.reserveRepository = reserveRepository;
    }

    /**
     * Создание отчета
     */
    public ReportEntity createReport() {
        ReportEntity reportEntity = new ReportEntity();
        List<UserEntity> users = userRepository.getAllByStatus(true);
        List<BookEntity> books = bookRepository.getAll();
        float reservesStat = 100f / books.size() * reserveRepository.findNotReturned().size();
        float reserveReturnStat = 100f / reserveRepository.getAll().size()
                * reserveRepository.findAllByDeadlineStatus(false).size();

        reportEntity.setDate(LocalDateTime.now());
        reportEntity.setActiveUsersCount((long) users.size());
        reportEntity.setBookCount((long) books.size());
        reportEntity.setBookReservationStatistic(reservesStat);
        reportEntity.setBookReturnStatistic(reserveReturnStat);
        reportRepository.save(reportEntity);
        return reportEntity;
    }

    /**
     * Получение всех отчетов в системе
     */
    public List<ReportEntity> getAllReports() {
        return reportRepository.getAll();
    }
}
