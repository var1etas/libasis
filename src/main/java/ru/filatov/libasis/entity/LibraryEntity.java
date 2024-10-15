package ru.filatov.libasis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class LibraryEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
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

    public LibraryEntity(Integer id, BookEntity book, UserEntity responsible, String addDate, String inStockCount, Float rate) {
        this.id = id;
        this.book = book;
        this.responsible = responsible;
        this.addDate = addDate;
        this.inStockCount = inStockCount;
        this.rate = rate;
    }

    public LibraryEntity(BookEntity book, UserEntity responsible, String addDate, String inStockCount, Float rate) {
        this.book = book;
        this.responsible = responsible;
        this.addDate = addDate;
        this.inStockCount = inStockCount;
        this.rate = rate;
    }

    public LibraryEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public BookEntity getBook() {
        return book;
    }

    public UserEntity getResponsible() {
        return responsible;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getInStockCount() {
        return inStockCount;
    }

    public Float getRate() {
        return rate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setResponsible(UserEntity responsible) {
        this.responsible = responsible;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public void setInStockCount(String inStockCount) {
        this.inStockCount = inStockCount;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "LibraryEntity{" +
                "id=" + id +
                ", book=" + book +
                ", responsible=" + responsible +
                ", addDate='" + addDate + '\'' +
                ", inStockCount='" + inStockCount + '\'' +
                ", rate=" + rate +
                '}';
    }
}
