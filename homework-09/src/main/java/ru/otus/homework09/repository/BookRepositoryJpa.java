package ru.otus.homework09.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework09.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        final Query query = em.createQuery("select count(b) from Book b", Long.class);
        return (long) query.getSingleResult();
    }

    @Override
    public Book save(final Book book) {
        if (book.getId() == 0) {
            em.persist(book);
        } else {
            return em.merge(book);
        }
        return book;
    }

    @Override
    public Book getById(final long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        final TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class);
//        final TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(final long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public Book getByName(final String name) {
        final TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
