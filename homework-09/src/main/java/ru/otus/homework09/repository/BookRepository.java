package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Book;

import java.util.List;

public interface BookRepository {

    long count();

    Book save(Book book);

    Book getById(long id);

    List<Book> getAll();

    int deleteById(long id);

    Book getByName(String name);
}
