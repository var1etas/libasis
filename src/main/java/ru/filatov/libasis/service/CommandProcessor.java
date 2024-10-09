package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandProcessor {
    private final BookServiceImpl bookService;

    @Autowired
    public CommandProcessor(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split("  ");
        switch (cmd[0])
        {
            case "create" ->
            {
                bookService.createBook(Integer.valueOf(cmd[1]), cmd[2], cmd[3], cmd[4]);
                System.out.println("Book created");
            }
            case "read" ->
            {
                System.out.println("There is your book: ");
                bookService.findById(Integer.valueOf(cmd[1]));
            }
            case "delete" ->
            {
                bookService.deleteById(Integer.valueOf(cmd[1]));
                System.out.println("Book deleted");
            }
            case "update author" ->
            {
                bookService.updateAuthor(Integer.valueOf(cmd[1]), cmd[2]);
                System.out.println("Author updated");
            }
            case "update title" ->
            {
                bookService.updateTitle(Integer.valueOf(cmd[1]), cmd[2]);
                System.out.println("Title updated");
            }
            case "update description" ->
            {
                bookService.updateDescription(Integer.valueOf(cmd[1]), cmd[2]);
                System.out.println("Description updated");
            }
            case "update status" ->
            {
                bookService.updateInStockStatus(Integer.valueOf(cmd[1]), Boolean.valueOf(cmd[2]));
                System.out.println("Status updated");
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

