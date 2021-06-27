package ru.otus.homework07.repository;

import ru.otus.homework07.entity.Book;

import java.util.List;

public interface BookRepository {

    int count();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
