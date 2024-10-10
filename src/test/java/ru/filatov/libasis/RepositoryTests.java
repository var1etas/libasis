package ru.filatov.libasis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.LibraryEntity;
import ru.filatov.libasis.entity.RoleEntity;
import ru.filatov.libasis.entity.UserEntity;
import ru.filatov.libasis.repository.BookRepository;
import ru.filatov.libasis.repository.LibraryRepository;
import ru.filatov.libasis.repository.RoleRepository;
import ru.filatov.libasis.repository.UserRepository;
import ru.filatov.libasis.repository.custom.LibraryRepositoryCustomImpl;
import ru.filatov.libasis.service.BookServiceImpl;

@SpringBootTest
class RepositoryTests {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final LibraryRepositoryCustomImpl libraryRepositoryCustom;
    private final LibraryRepository libraryRepository;
    private final BookServiceImpl bookService;

    @Autowired
    RepositoryTests(UserRepository userRepository, RoleRepository roleRepository, BookRepository bookRepository,
                    LibraryRepositoryCustomImpl libraryRepositoryCustom, LibraryRepository libraryRepository, BookServiceImpl bookService) {
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.libraryRepositoryCustom = libraryRepositoryCustom;
        this.libraryRepository = libraryRepository;
    }

    /** Поиск сущности по полю с помощью Query Lookup Strategies */
    @Test
    void findBookByTitle() {
        //generateBook();
        BookEntity foundBook = bookRepository.findByTitle("Steppenwolf").getLast();
        System.out.println(foundBook);
        Assertions.assertEquals(foundBook.getTitle(), "Steppenwolf");
    }

    /** Поиск сущности через связанную сущность на языке JPQL с использованием @Query */
    @Test
    void findUserByRole() {
        UserEntity foundUser = userRepository.findByRole("reader").getFirst();
        System.out.println(foundUser);
        Assertions.assertEquals(foundUser.getRole().getRole(), "reader");
    }

    /** Поиск сущности по полю с помощью Criteria API */
    @Test
    void findLibraryLotByRate() {
        LibraryEntity foundBook = libraryRepositoryCustom.findByRate(5f).getFirst();
        System.out.println(foundBook);
        Assertions.assertEquals(foundBook.getRate(), 5f);
    }

    /** Поиск сущности через связанную сущность с помощью Criteria API */
    @Test
    void findLibraryLotByBookAuthor() {
        //generateLibraryLot();
        BookEntity foundBook = libraryRepositoryCustom.findByAuthor("Joanne Rowling").getLast().getBook();
        System.out.println(foundBook);
        Assertions.assertEquals(foundBook.getAuthor(), "Joanne Rowling");
    }

    /** Тест транзакционной операции */
    @Test
    void transactionDelete() {
        bookService.deleteBook(702);
    }




    BookEntity generateBook() {
        BookEntity book = new BookEntity("Steppenwolf", "Herman Hesse", "Best book", "inStock");
        bookRepository.save(book);
        return book;
    }

    UserEntity generateUserWithRole() {
        RoleEntity userRole = new RoleEntity(1,"reader", "active", "superuser");
        UserEntity user = new UserEntity(302, userRole, "Alex", "123@gmail.com", "+78005553535");
        roleRepository.save(userRole);
        userRepository.save(user);
        return user;
    }

    void generateLibraryLot() {
        generateUserWithRole();
        UserEntity user = generateUserWithRole();
        BookEntity book = generateBook();
        LibraryEntity lot = new LibraryEntity(book, user, "10.10.2024", "3 pics", 4f);
        libraryRepository.save(lot);
    }


    void generator(){
        RoleEntity userRole = new RoleEntity("reader", "active", "superuser");
        UserEntity user = new UserEntity(userRole, "Alex", "123@gmail.com", "+78005553535");
        BookEntity book = new BookEntity("Harry Potter", "Joanne Rowling", "Fantasy book", "inStock");
        LibraryEntity lot = new LibraryEntity(book, user, "10.10.2024", "3 pics", 4f);
        roleRepository.save(userRole);
        userRepository.save(user);
        bookRepository.save(book);
        libraryRepository.save(lot);
    }
}

