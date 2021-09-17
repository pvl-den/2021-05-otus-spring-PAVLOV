package ru.otus.homework25.service.genre;

import ru.otus.homework25.entity.Genre;

public interface GenreService {

    Genre getById(long id);

    Genre getByName(String genreName);


}
