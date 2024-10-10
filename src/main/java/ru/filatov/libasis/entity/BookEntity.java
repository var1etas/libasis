package ru.filatov.libasis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String description;
    @Column
    private String status;
}
