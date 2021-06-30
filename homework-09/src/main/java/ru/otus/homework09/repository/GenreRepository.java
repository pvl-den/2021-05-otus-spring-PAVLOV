package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Genre;

public interface GenreRepository {

    Genre getById(long id);

}
