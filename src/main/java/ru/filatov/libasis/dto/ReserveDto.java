package ru.filatov.libasis.dto;

public record ReserveDto(
        BookDto book,
        UserDto user,
        String startDate,
        String finishDate,
        Boolean deadlineStatus) {
}
