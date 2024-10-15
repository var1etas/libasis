package ru.filatov.libasis.dto;

public record LibraryDto(
        BookDto book,
        UserDto responsible,
        String addDate,
        String inStockCount,
        Float rate) {
}
