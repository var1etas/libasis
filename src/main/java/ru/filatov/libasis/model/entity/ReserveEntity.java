package ru.filatov.libasis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reserves")
public class ReserveEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "finish_date")
    private String finishDate;

    @Column(name = "deadline_status")
    private Boolean deadlineStatus;

    public ReserveEntity(Long id, BookEntity book, UserEntity user, String startDate, String finishDate, Boolean deadlineStatus) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.deadlineStatus = deadlineStatus;
    }

    public ReserveEntity(BookEntity book, UserEntity user, String startDate, String finishDate, Boolean deadlineStatus) {
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.deadlineStatus = deadlineStatus;
    }

    public ReserveEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public BookEntity getBook() {
        return book;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public Boolean getDeadlineStatus() {
        return deadlineStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public void setDeadlineStatus(Boolean deadlineStatus) {
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
