package com.example.homework16.service;

import ru.otus.homework11.entity.Author;

import java.util.List;

public interface AuthorService {

    long count();

    Author save(Author author);

    Author getById(long id);

    Author getByName(String authorName);

    List<Author> getAll();

    void deleteById(long id);
}
