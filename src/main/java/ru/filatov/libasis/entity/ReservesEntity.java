package ru.filatov.libasis.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "reserves")
public class ReservesEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "finish_date")
    private Timestamp finishDate;

    @Column(name = "deadline_status")
    private String deadlineStatus;

    public Integer getId() {
        return id;
    }

    public BookEntity getBook() {
        return book;
    }

    public UserEntity getUser() {
        return user;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public String getDeadlineStatus() {
        return deadlineStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    public void setDeadlineStatus(String deadlineStatus) {
        this.deadlineStatus = deadlineStatus;
    }

    @Override
    public String toString() {
        return "ReservesEntity{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", deadlineStatus='" + deadlineStatus + '\'' +
                '}';
    }
}
