package ru.filatov.libasis.model.entity;

import jakarta.persistence.*;

/**
 * Сущность пользователя
 */
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String description;

    @Column
    private String genre;

    @Column
    private Boolean status;

    public BookEntity(Long id) {
        this.id = id;
    }

    public BookEntity(String title, String author, String description, String genre , Boolean status) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.status = status;
    }

    public BookEntity(String title, String author, String description, String genre) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
    }

    public BookEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                '}';
    }
}
