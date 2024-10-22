package ru.filatov.libasis.entity;

import jakarta.persistence.*;

import java.util.List;

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
    private Boolean status;
    @Column
    @OneToMany
    private List<GenreEntity> genres;

    public BookEntity(String title, String author, String description, Boolean status) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.status = status;
    }

    public BookEntity(Integer id, String title, String author, String description, Boolean status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.status = status;
    }

    public BookEntity() {
        super();
    }

    public Integer getId() {
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

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setId(Integer id) {
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

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", genres=" + genres +
                '}';
    }
}
