package ru.otus.homework09.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework09.entity.Note;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class NotesRepositoryJpa implements NotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Note save(Note note) {
        if (note.getId() == 0) {
            em.persist(note);
        } else {
            return em.merge(note);
        }
        return note;
    }

    @Override
    public Note getById(final long id) {
        final TypedQuery<Note> query = em.createQuery("select n from Note n where n.id = :id", Note.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Note getByBookId(final long bookId) {
        final TypedQuery<Note> query = em.createQuery("select n from Note n where n.book.id = :bookId", Note.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }

    @Override
    public List<Note> getAll() {
        final TypedQuery<Note> query = em.createQuery("select n from Note n join fetch n.book", Note.class);
        return query.getResultList();
    }

    @Override
    public int deleteById(final long id) {
        final Query query = em.createQuery("delete from Note n where n.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int deleteByBookId(final long bookId) {
        final Query query = em.createQuery("delete from Note n where n.book.id = :bookId");
        query.setParameter("bookId", bookId);
        return query.executeUpdate();
    }
}
