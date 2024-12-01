package ru.filatov.libasis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.model.dto.GenreRequestDto;
import ru.filatov.libasis.model.entity.GenreEntity;
import ru.filatov.libasis.model.repository.GenreRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public boolean createGenre(GenreRequestDto genreRequestDto) {
        GenreEntity genre = genreRepository.findByName(genreRequestDto.name());
        if (genre == null) {
            return false;
        }
        GenreEntity genreEntity = new GenreEntity(genreRequestDto.name(), genreRequestDto.description());
        genreRepository.save(genreEntity);
        return true;
    }

    public boolean updateGenre(Long genreId, GenreRequestDto genreRequestDto) {
        GenreEntity genreEntity = genreRepository.findByName(genreRequestDto.name());
        if(genreEntity == null) {
            return false;
        }
        genreEntity.setName(genreRequestDto.name());
        genreEntity.setDescription(genreRequestDto.description());
        genreRepository.save(genreEntity);
        return true;
    }

    public boolean deleteGenre(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(genreId);
        if(genreEntity.isEmpty()) {
            return false;
        }
        genreRepository.delete(genreEntity.get());
        return true;
    }

    public GenreEntity getGenre(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(genreId);
        if(genreEntity.isEmpty()) {
            throw new NoSuchElementException("Genre not found");
        }
        return genreEntity.get();
    }
}
