package ru.otus.homework09.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework09.entity.Note;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotesRepositoryJpa implements NotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Note save(Note note) {
        if (note.getId() == 0) {
            if (note.getBook().getNotes() == null) {
                note.getBook().setNotes(new ArrayList<>());
            }
            note.getBook().getNotes().add(note);
            em.persist(note);
        } else {
            return em.merge(note);
        }
        return note;
    }

    @Override
    public Note getById(final long id) {
        return em.find(Note.class, id);
    }

    @Override
    public List<Note> getAll() {
        final TypedQuery<Note> query = em.createQuery("select n from Note n join fetch n.book", Note.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(final long id) {
        Note note = em.find(Note.class, id);
        em.remove(note);
    }

    @Override
    public void deleteByBookId(final long bookId) {
        final Query query = em.createQuery("delete from Note n where n.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }
}
