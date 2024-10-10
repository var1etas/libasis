package ru.filatov.libasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.filatov.libasis.repository.BookRepository;
import ru.filatov.libasis.repository.custom.LibraryRepositoryCustomImpl;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LibraryRepositoryCustomImpl libraryRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, LibraryRepositoryCustomImpl libraryRepository, PlatformTransactionManager transactionManager) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteBook(Integer id) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try
        {
            libraryRepository.deleteByBookId(id);
            bookRepository.deleteById(id);
            transactionManager.commit(status);
        }
        catch (DataAccessException ex)
        {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
