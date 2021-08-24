package ru.otus.homework17.service;

import ru.otus.homework17.entity.Genre;

public interface GenreService {

    Genre getById(long id);

    Genre getByName(String genreName);


}
