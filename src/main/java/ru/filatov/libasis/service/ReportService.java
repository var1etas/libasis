package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final HtmlConverter htmlConverter;
    private ReportEntity reportEntity;
    private long usersTimer;
    private long booksTimer;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserRepository userRepository, BookRepository bookRepository, HtmlConverter htmlConverter) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.htmlConverter = htmlConverter;
    }

    public Integer startCreating() {
        reportEntity = new ReportEntity();
        reportEntity.setCreationStatus(ReportStatus.CREATED);
        reportEntity.setReport("В процессе...");
        reportRepository.save(reportEntity);

        CompletableFuture.supplyAsync(this::createReport);

        return reportEntity.getId();
    }

    private String createReport()  {
        long reportTimerStart = System.currentTimeMillis();

        StringBuilder usersReport = new StringBuilder();
        StringBuilder booksReport = new StringBuilder();

        Thread usersThread = new Thread(() -> {
            getAllUsers(usersReport);
        });
        Thread booksThread = new Thread(() -> {
            getAllBooks(booksReport);
        });

        usersThread.start();
        booksThread.start();

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
        long usersTimerStart = System.currentTimeMillis();
        List<String> users = new ArrayList<>();
        Iterable<UserEntity> iterableUsers = userRepository.findAll();
        iterableUsers.forEach(user -> users.add(user.toString()));
        usersReport.append(users);
        usersTimer = System.currentTimeMillis() - usersTimerStart;
    }

    private void getAllBooks(StringBuilder booksReport) {
        long booksTimerStart = System.currentTimeMillis();
        List<String> books = new ArrayList<>();
        Iterable<BookEntity> iterableBooks = bookRepository.findAll();
        iterableBooks.forEach(book -> books.add(book.toString()));
        booksReport.append(books);
        booksTimer = System.currentTimeMillis() - booksTimerStart;
    }

    public ReportEntity getReport(Integer reportId) {
        Optional<ReportEntity> reportEntity = reportRepository.findById(reportId);
        if (reportEntity.isEmpty()) {
            throw new NoSuchElementException("Report not found");
        } else if (reportEntity.get().getCreationStatus().equals(ReportStatus.CREATED)) {
            throw new NullPointerException("Report not completed");
        } else if (reportEntity.get().getCreationStatus().equals(ReportStatus.ERROR)) {
            throw new NullPointerException("Report generation error");
        }
        return reportEntity.get();
    }
}
