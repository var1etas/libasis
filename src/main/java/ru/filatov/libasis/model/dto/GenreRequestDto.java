package ru.filatov.libasis.model.dto;

import jakarta.validation.constraints.NotBlank;

public record GenreRequestDto(@NotBlank String name, @NotBlank String description) {
}
