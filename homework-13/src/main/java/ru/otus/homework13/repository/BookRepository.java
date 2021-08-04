package ru.otus.homework13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework13.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    //    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    Book findByName(String name);

}
