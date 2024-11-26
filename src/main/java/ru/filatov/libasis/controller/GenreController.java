package ru.filatov.libasis.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.dto.GenreRequestDto;
import ru.filatov.libasis.model.entity.GenreEntity;
import ru.filatov.libasis.model.service.GenreService;

@Validated
@RequestMapping("/api/genre")
@RestController
public class GenreController {
    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<String> createGenre(@RequestBody @Valid GenreRequestDto genre) {
        if(!genreService.createGenre(genre)) {
            return new ResponseEntity<>("Genre already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{genreId}")
    public ResponseEntity<String> updateGenre(@PathVariable Long genreId, @RequestBody @Valid GenreRequestDto genre) {
        if(!genreService.updateGenre(genreId, genre)) {
            return new ResponseEntity<>("Genre to update not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long genreId) {
        if(!genreService.deleteGenre(genreId)) {
            return new ResponseEntity<>("Genre not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{genreId}")
    public ResponseEntity<String> getGenres(@PathVariable Long genreId) {
        GenreEntity genre = genreService.getGenre(genreId);
        return new ResponseEntity<>(genre.toString(), HttpStatus.OK);
    }
}
