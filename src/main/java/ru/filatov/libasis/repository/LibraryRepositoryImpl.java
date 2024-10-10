package ru.filatov.libasis.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.LibraryEntity;

import java.util.List;

@Repository
public class LibraryRepositoryImpl implements LibraryRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public LibraryRepositoryImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<LibraryEntity> findByRate(Float rate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LibraryEntity> criteriaQuery = criteriaBuilder.createQuery(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaQuery.from(LibraryEntity.class);
        Predicate namePredicate = criteriaBuilder.equal(bookRoot.get("rate"), rate);
        criteriaQuery.select(bookRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<LibraryEntity> findByAuthor(String author)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LibraryEntity> criteriaQuery = criteriaBuilder.createQuery(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaQuery.from(LibraryEntity.class);
        Join<LibraryEntity, BookEntity> book = bookRoot.join("books", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(book.get("author"), author);
        criteriaQuery.select(bookRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public BookEntity findByBookId(Integer bookId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
        Root<BookEntity> bookRoot = criteriaQuery.from(BookEntity.class);
        Predicate idPredicate = criteriaBuilder.equal(bookRoot.get("id"), bookId);
        criteriaQuery.select(bookRoot).where(idPredicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    public void deleteByBookId(Integer bookId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<LibraryEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaDelete.from(LibraryEntity.class);
        Predicate idPredicate = criteriaBuilder.equal(bookRoot.get("id"), bookId);
        criteriaDelete.where(idPredicate);
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

}
