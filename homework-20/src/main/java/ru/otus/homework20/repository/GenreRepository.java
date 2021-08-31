package ru.otus.homework20.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homework20.entity.Genre;

public interface GenreRepository
        extends ReactiveMongoRepository<Genre, String> {

}