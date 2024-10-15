//package ru.filatov.libasis;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.filatov.libasis.entity.BookEntity;
//import ru.filatov.libasis.entity.LibraryEntity;
//import ru.filatov.libasis.entity.RoleEntity;
//import ru.filatov.libasis.entity.UserEntity;
//import ru.filatov.libasis.repository.BookRepository;
//import ru.filatov.libasis.repository.LibraryRepository;
//import ru.filatov.libasis.repository.RoleRepository;
//import ru.filatov.libasis.repository.UserRepository;
//import ru.filatov.libasis.repository.custom.LibraryRepositoryCustomImpl;
//import ru.filatov.libasis.service.crud.BookService;
//
//@SpringBootTest
//class RepositoryTests {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final BookRepository bookRepository;
//    private final LibraryRepositoryCustomImpl libraryRepositoryCustom;
//    private final LibraryRepository libraryRepository;
//    private final BookService bookService;
//
//    @Autowired
//    RepositoryTests(UserRepository userRepository, RoleRepository roleRepository, BookRepository bookRepository,
//                    LibraryRepositoryCustomImpl libraryRepositoryCustom, LibraryRepository libraryRepository, BookService bookService) {
//        this.bookService = bookService;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.bookRepository = bookRepository;
//        this.libraryRepositoryCustom = libraryRepositoryCustom;
//        this.libraryRepository = libraryRepository;
//    }
//
//    /** Поиск сущности по полю с помощью Query Lookup Strategies */
//    @Test
//    void findBookByTitle() {
//        //generateBook();
//        BookEntity foundBook = bookRepository.findByTitle("Steppenwolf").getLast();
//        System.out.println(foundBook);
//        Assertions.assertEquals(foundBook.getTitle(), "Steppenwolf");
//    }
//
//    /** Поиск сущности через связанную сущность на языке JPQL с использованием @Query */
//    @Test
//    void findUserByRole() {
//        UserEntity foundUser = userRepository.findByRole("reader").getFirst();
//        System.out.println(foundUser);
//        Assertions.assertEquals(foundUser.getRole().getName(), "reader");
//    }
//
//    /** Поиск сущности по полю с помощью Criteria API */
//    @Test
//    void findLibraryLotByRate() {
//        LibraryEntity foundBook = libraryRepositoryCustom.findByRate(5f).getFirst();
//        System.out.println(foundBook);
//        Assertions.assertEquals(foundBook.getRate(), 5f);
//    }
//
//    /** Поиск сущности через связанную сущность с помощью Criteria API */
//    @Test
//    void findLibraryLotByBookAuthor() {
//        //generateLibraryLot();
//        BookEntity foundBook = libraryRepositoryCustom.findByAuthor("Joanne Rowling").getLast().getBook();
//        System.out.println(foundBook);
//        Assertions.assertEquals(foundBook.getAuthor(), "Joanne Rowling");
//    }
//
//    /** Тест транзакционной операции */
//    @Test
//    void transactionDelete() {
//        bookService.delete(952);
//    }
//
//
//    BookEntity generateBook() {
//        BookEntity book = new BookEntity("Steppenwolf", "Herman Hesse", "Best book", "inStock");
//        bookRepository.save(book);
//        return book;
//    }
//
//    @Test
//    void generateUserWithRole() {
//        RoleEntity userRole = new RoleEntity(302,"reader", "active", "superuser");
//        UserEntity user = new UserEntity(userRole, "Anya", "12345@gmail.com", "+78996663535");
//        roleRepository.save(userRole);
//        userRepository.save(user);
//    }
//    @Test
//    void generateLibraryLot() {
//        BookEntity book = new BookEntity("1984", "Orwell", "Fantasy book", "inStock");
//        bookRepository.save(book);
//        RoleEntity userRole = new RoleEntity(352,"admin", "active", "superuser");
//        UserEntity user = new UserEntity(702, userRole, "Petya", "1234@gmail.com", "+78995553535");
//        LibraryEntity library = new LibraryEntity(book, user, "15.10.2024", "2 pics", 4.8f);
//        libraryRepository.save(library);
//    }
//
//
//    void generator(){
//        RoleEntity userRole = new RoleEntity("reader", "active", "superuser");
//        UserEntity user = new UserEntity(userRole, "Alex", "123@gmail.com", "+78005553535");
//        BookEntity book = new BookEntity("Harry Potter", "Joanne Rowling", "Fantasy book", "inStock");
//        LibraryEntity lot = new LibraryEntity(book, user, "10.10.2024", "3 pics", 4f);
//        roleRepository.save(userRole);
//        userRepository.save(user);
//        bookRepository.save(book);
//        libraryRepository.save(lot);
//    }
//}
//
