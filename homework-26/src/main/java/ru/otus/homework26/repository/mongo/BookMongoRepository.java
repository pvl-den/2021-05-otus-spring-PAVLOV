package ru.otus.homework26.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework26.entity.mongo.Book;

import java.util.List;

public interface BookMongoRepository extends MongoRepository<Book, String> {

    List<Book> findAll();

}
