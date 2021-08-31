package ru.otus.homework20.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.homework20.entity.Author;
import ru.otus.homework20.entity.Book;
import ru.otus.homework20.entity.Genre;
import ru.otus.homework20.entity.Note;
import ru.otus.homework20.repository.BookRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookControllerTest {

    @Autowired
    BookController bookController;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void allBookTest(){
        Genre genre = Genre.builder().id("1").name("genreTest1").build();
        Author author = Author.builder().id("1").name("authorTest1").build();
        List<Note> notes = List.of(new Note("noteTest1", new Date(), "authorTest1"), new Note("noteTest2", new Date(), "authorTest2"));

        Book book1 = Book.builder().id("-1").name("bookName1").author(author).genre(genre).notes(notes).build();
        Book book2 = Book.builder().id("-2").name("bookName2").author(author).genre(genre).notes(notes).build();

        when(bookRepository.findAll()).thenReturn(Flux.just(book1, book2));

        WebTestClient client = WebTestClient
                .bindToController(bookController)
                .build();

        client.get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk();
    }

}