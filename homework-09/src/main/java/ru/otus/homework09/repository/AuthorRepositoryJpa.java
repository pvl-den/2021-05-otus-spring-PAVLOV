package ru.otus.homework09.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework09.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        final Query query = em.createQuery("select count(a) from Author a");
        return (long) query.getSingleResult();
    }

    @Override
    public Author save(final Author author) {
        if (author.getId() == 0) {
            em.persist(author);
        } else {
            return em.merge(author);
        }
        return author;
    }

    @Override
    public Author getById(final long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author getByName(final String name) {
        final TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        final TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(final long id) {
        Author author = em.find(Author.class, id);
        em.remove(author);
    }
}
