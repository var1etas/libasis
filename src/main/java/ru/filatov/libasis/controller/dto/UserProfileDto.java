package ru.filatov.libasis.controller.dto;

/**
 * Профиль пользователя для просмотра в личном кабинете
 */
public record UserProfileDto(String name, String login, Float statistic, Long reservesCount) {
}
