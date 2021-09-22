package ru.otus.homework26.service;

import ru.otus.homework26.entity.mongo.Genre;
import ru.otus.homework26.entity.relationalstorage.RS_Genre;

public interface GenreService {

    RS_Genre convert(Genre genre);

    RS_Genre findByMongoId(String id);
}
