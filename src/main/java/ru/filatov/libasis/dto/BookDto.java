package ru.filatov.libasis.dto;

public record BookDto(
        String title,
        String author,
        String description,
        Boolean status) {
}
