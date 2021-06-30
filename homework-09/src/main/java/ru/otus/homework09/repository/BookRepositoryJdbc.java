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
public class BookRepositoryJdbc implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        final Query query = em.createQuery("select count(b.id) from Book b", Integer.class);
        return query.executeUpdate();
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
        final TypedQuery<Book> query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        final TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public int deleteById(final long id) {
        final Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();

    }

    @Override
    public Book getByName(final String name) {
        final TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
