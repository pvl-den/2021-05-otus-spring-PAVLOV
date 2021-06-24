package ru.otus.homework07.service;

import ru.otus.homework07.entity.Author;

import java.util.List;

public interface AuthorService {

    int count();

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
