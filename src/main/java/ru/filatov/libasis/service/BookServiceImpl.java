package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.repository.BookRepository;

import java.util.Scanner;
import java.util.regex.Pattern;

@Service
public class BookServiceImpl implements BookService {
    BookRepository repository;
    Scanner sc;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
        sc = new Scanner(System.in);
        sc.useDelimiter(Pattern.compile("(\\n)|;"));
    }

    @Override
    public void createBook() {
        BookEntity book = new BookEntity();
        System.out.print("Введите id: ");
        book.setId(sc.nextInt());
        System.out.print("Введите название: ");
        book.setTitle(sc.next());
        System.out.print("Введите автора: ");
        book.setAuthor(sc.next());
        System.out.print("Введите описание: ");
        book.setDescription(sc.next());
        book.setInStock(true);

        repository.create(book);
        System.out.println("Book " + book.getTitle() + " created");
    }

    @Override
    public void findById() {
        System.out.print("Введите id для поиска: ");
        Integer id = sc.nextInt();
        System.out.println(repository.read(id));
    }

    @Override
    public void deleteById() {
        System.out.print("Введите id для удаления: ");
        Integer id = sc.nextInt();

        repository.delete(id);
        System.out.println("Book " + id + " deleted");
    }

    @Override
    public void updateDescription() {
        System.out.print("Введите id: ");
        Integer id = sc.nextInt();
        System.out.print("Введите новое описание: ");
        String newDescription = sc.next();
        BookEntity book = repository.read(id);
        book.setDescription(newDescription);

        repository.update(book);
        System.out.println("Book " + id + " description updated");
    }

    @Override
    public void updateAuthor() {
        System.out.print("Введите id: ");
        Integer id = sc.nextInt();
        System.out.print("Введите нового автора: ");
        String newAuthor = sc.next();
        BookEntity book = repository.read(id);
        book.setAuthor(newAuthor);

        repository.update(book);
        System.out.println("Book " + id + " author updated");
    }

    @Override
    public void updateTitle() {
        System.out.print("Введите id: ");
        Integer id = sc.nextInt();
        System.out.print("Введите новое название: ");
        String newTitle = sc.next();
        BookEntity book = repository.read(id);
        book.setTitle(newTitle);

        repository.update(book);
        System.out.println("Book " + id + " title updated");
    }

    @Override
    public void updateInStockStatus() {
        System.out.print("Введите id: ");
        Integer id = sc.nextInt();
        System.out.print("Введите новый статус: ");
        Boolean newStatus = sc.nextBoolean();
        BookEntity book = repository.read(id);
        book.setInStock(newStatus);

        repository.update(book);
        System.out.println("Book " + id + " status updated");
    }
}
