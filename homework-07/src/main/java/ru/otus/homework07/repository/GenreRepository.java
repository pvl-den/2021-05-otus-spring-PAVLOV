package ru.otus.homework07.repository;

import ru.otus.homework07.entity.Genre;

public interface GenreRepository {

    Genre getById(long id);

}
