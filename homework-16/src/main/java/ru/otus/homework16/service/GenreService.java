package ru.otus.homework16.service;

import ru.otus.homework16.entity.Genre;

public interface GenreService {

    Genre getById(long id);

    Genre getByName(String genreName);


}
