package ru.filatov.libasis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.filatov.libasis.service.BookGenerator;
import ru.filatov.libasis.service.BookServiceImpl;

import java.util.Scanner;

@SpringBootApplication
public class LibasisApplication implements CommandLineRunner {
    BookServiceImpl bookService;
    BookGenerator bookGenerator;

    @Autowired
    public LibasisApplication(BookServiceImpl bookService, BookGenerator bookGenerator) {
        this.bookService = bookService;
        this.bookGenerator = bookGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibasisApplication.class, args);
    }
    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter command: ");
            String name = sc.nextLine();
            switch (name) {
                case "create":
                    bookService.createBook();
                    break;
                case "read":
                    bookService.findById();
                    break;
                case "delete":
                    bookService.deleteById();
                    break;
                case "update description":
                    bookService.updateDescription();
                    break;
                case "update author":
                    bookService.updateAuthor();
                    break;
                case "update title":
                    bookService.updateTitle();
                    break;
                case "update status":
                    bookService.updateInStockStatus();
                    break;
                case "generate":
                    bookGenerator.generate();
                    break;
                default:
                    System.out.println("Unknown command, try again...");
                    break;
            }
        }
    }
}
