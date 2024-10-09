package ru.filatov.libasis.entity;

public class BookEntity {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Boolean inStock;

    public BookEntity(Integer id, String title, String author, String description, Boolean inStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.inStock = inStock;
    }

    public BookEntity() {
        super();
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

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
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

    public Boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
