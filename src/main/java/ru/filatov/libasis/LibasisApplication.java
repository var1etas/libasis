package ru.filatov.libasis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.filatov.libasis.config.CommandLineConfig;
import ru.filatov.libasis.service.BookServiceImpl;

@SpringBootApplication
public class LibasisApplication{
    public static void main(String[] args) {
        SpringApplication.run(LibasisApplication.class, args);
    }
}
