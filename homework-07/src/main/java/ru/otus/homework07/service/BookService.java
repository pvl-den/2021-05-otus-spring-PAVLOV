package ru.otus.homework07.service;

import ru.otus.homework07.entity.Book;

import java.util.List;

public interface BookService {

    int count();

    boolean insert(Book book);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(long id);

    Boolean createBook(String name, long authorId, long genreId);
}
