package ru.otus.homework07.service;

import ru.otus.homework07.entity.Book;

import java.util.List;

public interface BookService {

    int count();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
