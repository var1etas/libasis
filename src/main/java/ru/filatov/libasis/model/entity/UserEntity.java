package ru.filatov.libasis.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Сущность пользователя
 */
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Column
    private Float statistic;

    @Column
    private Long reservesCount;

    @Column
    private Boolean status;

    public UserEntity() {
        super();
    }

    public UserEntity(String name, String login, String password, Role role, Float statistic, Boolean status) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.statistic = statistic;
        this.status = status;
    }

    public UserEntity(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = Role.ROLE_USER;
        this.statistic = 100f;
        this.status = Boolean.TRUE;
    }

    public Float getStatistic() {
        return statistic;
    }

    public void setStatistic(Float statistic) {
        this.statistic = statistic;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getReservesCount() {
        return reservesCount;
    }

    public void setReservesCount(Long reservesCount) {
        this.reservesCount = reservesCount;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", statistic=" + statistic +
                ", reservesCount=" + reservesCount +
                ", status=" + status +
                '}';
    }
}
