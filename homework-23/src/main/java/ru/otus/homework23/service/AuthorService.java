package ru.otus.homework23.service;

import ru.otus.homework23.entity.Author;

import java.util.List;

public interface AuthorService {

    long count();

    Author save(Author author);

    Author getById(long id);

    Author getByName(String authorName);

    List<Author> getAll();

    void deleteById(long id);
}
