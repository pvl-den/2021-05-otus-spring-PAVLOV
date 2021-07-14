package ru.otus.homework11.service;

import ru.otus.homework11.entity.Book;

import java.util.List;

public interface BookService {

    long count();

    Book save(Book book);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(long id);

    Book createBook(String name, long authorId, long genreId);
}
