package ru.filatov.libasis.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.GenreEntity;
import ru.filatov.libasis.repository.GenreRepository;

import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public void save(GenreEntity genre) {
        genreRepository.save(genre);
    }

    public Iterable<GenreEntity> findAll() {
        return genreRepository.findAll();
    }

    public Optional<GenreEntity> findById(Integer id) {
        return genreRepository.findById(id);
    }

}
