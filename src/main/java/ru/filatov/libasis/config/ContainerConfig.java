package ru.filatov.libasis.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.filatov.libasis.entity.BookEntity;

@Configuration
public class ContainerConfig {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<BookEntity> userContainer() {
        return new ArrayList<>();
    }
}

