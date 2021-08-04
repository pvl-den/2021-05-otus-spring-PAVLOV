package ru.otus.homework13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework13.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
