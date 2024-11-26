package ru.filatov.libasis.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@NotBlank String name, @NotBlank String login, @NotBlank String password) {
}
