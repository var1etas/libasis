package ru.filatov.libasis.service.crud;

public interface EntityService<T> {
    void create(T Entity);
    T read(Integer id);
    void update(Integer id, T Entity);
    void delete(Integer id);
}

