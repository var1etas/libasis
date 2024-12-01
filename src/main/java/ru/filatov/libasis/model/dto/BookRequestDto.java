package ru.filatov.libasis.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequestDto(@NotBlank String title, @NotBlank String author, @NotBlank String description, @NotBlank String genre) {
}
