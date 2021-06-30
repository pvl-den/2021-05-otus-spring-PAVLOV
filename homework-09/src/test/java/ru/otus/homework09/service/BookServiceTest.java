package ru.otus.homework09.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.homework09.entity.Author;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.entity.Genre;
import ru.otus.homework09.repository.BookRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ActiveProfiles("test")
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

        int expectedCount = 4;

        given(bookRepository.count()).willReturn(expectedCount);

        assertThat(bookService.count()).isEqualTo(expectedCount);
    }

    @Test
    void insert() {
        Book book = getBook();

        assertEquals(bookService.save(book), book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getByName() {
        Book book = getBook();

        given(bookRepository.getByName(book.getName())).willReturn(book);

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
        given(bookRepository.getAll()).willReturn(Collections.singletonList(book));

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

        assertEquals(bookService.createBook(testCreateBookName.getName(),
                testCreateBookName.getAuthor().getId(),
                testCreateBookName.getAuthor().getId()), testCreateBookName);

//        verify(bookRepository, times(1)).save(testCreateBookName);
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(1, "nameGenre"))
                .author(new Author(1, "nameAuthor"))
                .build();
    }
}