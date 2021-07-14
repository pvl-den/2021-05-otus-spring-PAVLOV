package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework11.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
