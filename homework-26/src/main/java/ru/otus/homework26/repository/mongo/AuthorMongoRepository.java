package ru.otus.homework26.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework26.entity.mongo.Author;

public interface AuthorMongoRepository extends MongoRepository<Author, String> {

}
