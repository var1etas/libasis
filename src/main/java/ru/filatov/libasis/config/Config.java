package ru.filatov.libasis.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    @PersistenceContext
    EntityManager EntityManager;

    @Bean
    public EntityManager entityManager() {
        return EntityManager;
    }
}
