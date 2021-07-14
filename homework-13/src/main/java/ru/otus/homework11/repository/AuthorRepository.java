package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework11.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);

}
