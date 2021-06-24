package ru.otus.homework07.repository;

import ru.otus.homework07.entity.Author;

import java.util.List;

public interface AuthorRepository {

    int count();

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
