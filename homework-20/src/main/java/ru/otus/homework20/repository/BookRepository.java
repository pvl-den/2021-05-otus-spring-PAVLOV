package ru.otus.homework20.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework20.entity.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    @Override
    Flux<Book> findAll();

    Mono<Book> findByName(String name);

}