package ru.filatov.libasis.service;

public interface BookService {
    void createBook(Integer id, String title, String author, String description);
    void findById(Integer id);
    void deleteById(Integer id);
    void updateDescription(Integer id, String description);
    void updateAuthor(Integer id, String author);
    void updateTitle(Integer id, String title);
    void updateInStockStatus(Integer id, Boolean inStock);
}
