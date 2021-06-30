package ru.otus.homework09.service;

import ru.otus.homework09.entity.Book;

import java.util.List;

public interface BookService {

    int count();

    Book save(Book book);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getAll();

    int deleteById(long id);

    Book createBook(String name, long authorId, long genreId);
}
