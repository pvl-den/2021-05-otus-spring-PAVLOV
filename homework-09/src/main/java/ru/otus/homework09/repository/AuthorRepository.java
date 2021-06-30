package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Author;

import java.util.List;

public interface AuthorRepository {

    int count();

    Author save(Author author);

    Author getById(long id);

    Author getByName(String authorName);

    List<Author> getAll();

    int deleteById(long id);
}
