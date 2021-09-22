package ru.otus.homework26.service;

import ru.otus.homework26.entity.mongo.Book;
import ru.otus.homework26.entity.relationalstorage.RS_Book;

public interface BookService {

    RS_Book convert(Book book);
}
