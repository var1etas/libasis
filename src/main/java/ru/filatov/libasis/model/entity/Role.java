package ru.filatov.libasis.model.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роль пользователя в системе
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
