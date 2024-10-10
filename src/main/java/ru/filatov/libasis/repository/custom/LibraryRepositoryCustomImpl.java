package ru.filatov.libasis.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.LibraryEntity;

import java.util.List;

@Repository
public class LibraryRepositoryCustomImpl implements LibraryRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public LibraryRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LibraryEntity> findByRate(Float rate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LibraryEntity> criteriaQuery = criteriaBuilder.createQuery(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaQuery.from(LibraryEntity.class);
        Predicate namePredicate = criteriaBuilder.equal(bookRoot.get("rate"), rate);
        criteriaQuery.select(bookRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<LibraryEntity> findByAuthor(String author) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LibraryEntity> criteriaQuery = criteriaBuilder.createQuery(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaQuery.from(LibraryEntity.class);
        Join<LibraryEntity, BookEntity> book = bookRoot.join("book", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(book.get("author"), author);
        criteriaQuery.select(bookRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void deleteByBookId(Integer bookId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<LibraryEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(LibraryEntity.class);
        Root<LibraryEntity> bookRoot = criteriaDelete.from(LibraryEntity.class);
        Join<LibraryEntity, BookEntity> book = bookRoot.join("book", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(book.get("id"), bookId);
        criteriaDelete.where(namePredicate);
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
