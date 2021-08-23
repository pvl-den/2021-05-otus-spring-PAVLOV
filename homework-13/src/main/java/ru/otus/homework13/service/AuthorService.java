package ru.otus.homework13.service;

import ru.otus.homework13.entity.Author;

import java.util.List;

public interface AuthorService {

    long count();

    Author save(Author author);

    Author getById(String id);

    Author getByName(String authorName);

    List<Author> getAll();

    void deleteById(String id);
}
