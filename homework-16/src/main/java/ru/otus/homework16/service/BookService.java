package ru.otus.homework16.service;

import ru.otus.homework16.entity.Book;

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
