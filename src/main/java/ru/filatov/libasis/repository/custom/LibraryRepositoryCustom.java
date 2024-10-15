package ru.filatov.libasis.repository.custom;

import ru.filatov.libasis.entity.LibraryEntity;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<LibraryEntity> findByRate(Float rate);
    List<LibraryEntity> findByAuthor(String author);
}
