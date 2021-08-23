package ru.otus.homework13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework13.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);

}
