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
}
