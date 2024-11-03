package ru.filatov.libasis.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.ReportEntity;
import ru.filatov.libasis.entity.ReportStatus;
import ru.filatov.libasis.entity.UserEntity;
import ru.filatov.libasis.repository.BookRepository;
import ru.filatov.libasis.repository.ReportRepository;
import ru.filatov.libasis.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final HtmlConverter htmlConverter;
    private ReportEntity reportEntity;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, BookRepository bookRepository, HtmlConverter htmlConverter) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.htmlConverter = htmlConverter;
    }

    public Integer startCreating() {
        reportEntity = new ReportEntity();
        CompletableFuture.supplyAsync(this::createReport);
        return reportEntity.getId();
    }

    /**
     * Создает и сохраняет отчет
     */
    private String createReport()  {
        reportEntity.setCreationStatus(ReportStatus.CREATED);
        reportEntity.setReport("В процессе...");
        reportRepository.save(reportEntity);

        long reportTimerStart = System.currentTimeMillis();

        StringBuilder usersReport = new StringBuilder();
        StringBuilder booksReport = new StringBuilder();

        Thread usersThread = new Thread(() -> {
            getAllUsers(usersReport);
        });
        Thread booksThread = new Thread(() -> {
            getAllBooks(booksReport);
        });

        long usersTimerStart = System.currentTimeMillis();
        usersThread.start();
        long usersTimer = System.currentTimeMillis() - usersTimerStart;
        long booksTimerStart = System.currentTimeMillis();
        booksThread.start();
        long booksTimer = System.currentTimeMillis() - booksTimerStart;

        try {
            usersThread.join();
            booksThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            reportEntity.setCreationStatus(ReportStatus.ERROR);
            reportRepository.save(reportEntity);
            return "Ошибка";
        }

        long reportTimer = System.currentTimeMillis() - reportTimerStart;

        reportEntity.setReport(htmlConverter.generatePage(usersReport, booksReport, usersTimer, booksTimer, reportTimer).toString());
        reportEntity.setCreationStatus(ReportStatus.COMPLETED);
        reportRepository.save(reportEntity);

        return "Завершен";
    }

    private void getAllUsers(StringBuilder usersReport) {
        List<String> users = new ArrayList<>();
        Iterable<UserEntity> iterableUsers = userRepository.findAll();
        iterableUsers.forEach(user -> users.add(user.toString()));
        usersReport.append(users);
    }

    private void getAllBooks(StringBuilder booksReport) {
        List<String> books = new ArrayList<>();
        Iterable<BookEntity> iterableBooks = bookRepository.findAll();
        iterableBooks.forEach(book -> books.add(book.toString()));
        booksReport.append(books);
    }

    public ReportEntity getReport(Integer reportId) {
        Optional<ReportEntity> reportEntity = reportRepository.findById(reportId);
        if (!reportEntity.isPresent()) {
            throw new NoSuchElementException("Report not found");
        } else if (reportEntity.get().getCreationStatus().equals(ReportStatus.CREATED)) {
            throw new NullPointerException("Report not completed");
        } else if (reportEntity.get().getCreationStatus() == ReportStatus.ERROR) {
            throw new NullPointerException("Report generation error");
        }
        return reportEntity.get();
    }
}
