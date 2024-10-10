package ru.filatov.libasis.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "library")
public class LibraryEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private UserEntity responsible;

    @Column(name = "add_date")
    private String addDate;

    @Column(name = "in_stock_count")
    private String inStockCount;

    @Column
    private Float rate;
}
