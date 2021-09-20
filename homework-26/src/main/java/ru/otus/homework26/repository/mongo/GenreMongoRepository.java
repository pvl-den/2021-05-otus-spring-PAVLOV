package ru.otus.homework26.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework26.entity.mongo.Genre;

public interface GenreMongoRepository extends MongoRepository<Genre, String> {

}
