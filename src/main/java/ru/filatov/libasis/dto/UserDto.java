package ru.filatov.libasis.dto;

public record UserDto(
        RoleDto role,
        String name,
        String email,
        String phoneNumber) {
}
