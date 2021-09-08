package ru.otus.homework23.service;

import ru.otus.homework23.entity.Genre;

public interface GenreService {

    Genre getById(long id);

    Genre getByName(String genreName);


}
