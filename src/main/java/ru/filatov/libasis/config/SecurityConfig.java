package ru.filatov.libasis.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация бинов связанных с безопасностью
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Создание кодировщика паролей
     */
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создание цепочки фильтров, доступ к админ-панели только у администраторов системы
     * @param http запрос
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/api/user/signUp", "/login", "/logout")
                        .permitAll()
                        .requestMatchers("/api/adminpanel/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/");
                        }));

        return http.build();
    }
}
