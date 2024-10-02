package ru.filatov.libasis.service;

import ru.filatov.libasis.entity.BookEntity;

public interface BookService {
    void createBook();
    void findById();
    void deleteById();
    void updateDescription();
    void updateAuthor();
    void updateTitle();
    void updateInStockStatus();
}
