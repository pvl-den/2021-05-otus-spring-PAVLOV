package ru.otus.homework20.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework20.entity.Book;
import ru.otus.homework20.repository.BookRepository;


@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public Flux<Book> allBooks() {
        return bookRepository.findAll();
    }

}