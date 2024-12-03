package ru.filatov.libasis.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Сущность отчета
 */
@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column
    private Long bookCount;

    @Column
    private Long activeUsersCount;

    @Column(name = "reserved_books")
    private Float bookReservationStatistic;

    @Column(name = "returned_books")
    private Float bookReturnStatistic;

    public ReportEntity() {
        super();
    }

    public ReportEntity(LocalDateTime date, Long bookCount, Long activeUsersCount, Float bookReservationStatistic, Float bookReturnStatistic) {
        this.bookCount = bookCount;
        this.activeUsersCount = activeUsersCount;
        this.bookReservationStatistic = bookReservationStatistic;
        this.bookReturnStatistic = bookReturnStatistic;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    public Long getActiveUsersCount() {
        return activeUsersCount;
    }

    public void setActiveUsersCount(Long activeUsersCount) {
        this.activeUsersCount = activeUsersCount;
    }

    public Float getBookReservationStatistic() {
        return bookReservationStatistic;
    }

    public void setBookReservationStatistic(Float bookReservationStatistic) {
        this.bookReservationStatistic = bookReservationStatistic;
    }

    public Float getBookReturnStatistic() {
        return bookReturnStatistic;
    }

    public void setBookReturnStatistic(Float bookReturnStatistic) {
        this.bookReturnStatistic = bookReturnStatistic;
    }
}
