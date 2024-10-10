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

    public BookEntity(String title, String author, String description, String status) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.status = status;
    }

    public BookEntity(Integer id, String title, String author, String description, String status) {
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

    public String getStatus() {
        return status;
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
