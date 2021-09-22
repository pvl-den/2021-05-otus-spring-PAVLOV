package ru.otus.homework26.service;

import ru.otus.homework26.entity.mongo.Author;
import ru.otus.homework26.entity.relationalstorage.RS_Author;

public interface AuthorService {

    RS_Author convert(Author author);

    RS_Author findByMongoId(String mongoId);
}
