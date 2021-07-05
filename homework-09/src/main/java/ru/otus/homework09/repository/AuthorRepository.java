package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Author;

import java.util.List;

public interface AuthorRepository {

    long count();

    Author save(Author author);

    Author getById(long id);

    Author getByName(String authorName);

    List<Author> getAll();

    void deleteById(long id);
}
