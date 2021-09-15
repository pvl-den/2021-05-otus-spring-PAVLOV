package ru.otus.homework25.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import ru.otus.homework25.entity.Author;
import ru.otus.homework25.entity.Book;
import ru.otus.homework25.entity.Genre;
import ru.otus.homework25.repository.BookRepository;
import ru.otus.homework25.service.author.AuthorService;
import ru.otus.homework25.service.book.BookService;
import ru.otus.homework25.service.book.BookServiceImpl;
import ru.otus.homework25.service.genre.GenreService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorService authorService;

    @MockBean
    GenreService genreService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, authorService, genreService);
    }

    @Test
    void count() {

        long expectedCount = 4;

        given(bookRepository.count()).willReturn(expectedCount);

        assertThat(bookService.count()).isEqualTo(expectedCount);
    }

    @Test
    void insert() {
        Book book = getBook();
        given(bookRepository.save(book)).willReturn(book);

        Book actualBook = bookService.save(book);

        assertEquals(actualBook, book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getByName() {
        Book book = getBook();

        given(bookRepository.findByName(book.getName())).willReturn(book);

        assertThat(bookService.getByName(book.getName())).isEqualTo(book);
    }

    @Test
    void getById() {
        Book book = getBook();

        given(bookRepository.getById(book.getId())).willReturn(book);

        assertThat(bookService.getById(book.getId())).isEqualTo(book);
    }

    @Test
    void getAll() {
        Book book = getBook();
        given(bookRepository.findAll()).willReturn(Collections.singletonList(book));

        assertThat(bookService.getAll()).isEqualTo(Collections.singletonList(book));
    }

    @Test
    void deleteById() {

        Book book = getBook();
        bookService.save(book);
        bookService.deleteById(book.getId());

        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    void createBook() {
        Author testAuthor_1 = Author.builder().id(1).name("testAuthor_1").build();
        Genre testGenre_1 = Genre.builder().id(1).name("testGenre_1").build();
        given(authorService.getById(1)).willReturn(testAuthor_1);
        given(genreService.getById(1)).willReturn(testGenre_1);

        Book testCreateBookName = Book.builder()
                .name("testCreateBookName")
                .author(testAuthor_1)
                .genre(testGenre_1)
                .build();

        Book savedBook = Book.builder()
                .name("testCreateBookName")
                .author(testAuthor_1)
                .genre(testGenre_1)
                .notes(null)
                .build();

        given(bookRepository.save(testCreateBookName)).willReturn(testCreateBookName);

        assertEquals(testCreateBookName, bookService.createBook(testCreateBookName.getName(),
                testCreateBookName.getAuthor().getId(),
                testCreateBookName.getAuthor().getId()));

        verify(bookRepository, times(1)).save(savedBook);
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(0, "nameGenre"))
                .author(new Author(0, "nameAuthor"))
                .build();
    }
}