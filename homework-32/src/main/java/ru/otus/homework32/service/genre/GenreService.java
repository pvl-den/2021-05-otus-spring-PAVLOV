package ru.otus.homework32.service.genre;

import ru.otus.homework32.entity.Genre;

public interface GenreService {

    Genre getById(long id);

    Genre getByName(String genreName);


}
