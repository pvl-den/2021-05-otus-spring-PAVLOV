package ru.otus.homework09.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework09.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre getById(final long id) {
        return em.find(Genre.class, id);
    }

}
