package ru.otus.homework13.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework13.entity.Author;
import ru.otus.homework13.entity.Book;
import ru.otus.homework13.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void countBookTest() {
        long countBooks = bookRepository.count();
        assertThat(countBooks).isEqualTo(4);
    }

    @Test
    void insertBookTest() {
        Author testAuthor = Author.builder().id("1").name("testAuthor").build();
        Genre testGenre = Genre.builder().id("1").name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        Book save = bookRepository.save(testBook);

        List<String> collectNames = bookRepository.findAll().stream().map(Book::getName).collect(Collectors.toList());

        assertTrue(collectNames.contains(testBook.getName()));
    }

    @Test
    void getById() {

        Author testAuthor = Author.builder().id("1").name("testAuthor").build();
        Genre testGenre = Genre.builder().id("1").name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        bookRepository.save(testBook);
        String bookId = bookRepository.findByName("testBookForInsertTest").getId();

        assertThat(bookRepository.findById(bookId).get().getName()).isEqualTo(testBook.getName());
    }

    @Test
    void getAll() {
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks.size()).isEqualTo(4);
    }

    @Test
    void deleteById() {

        Author testAuthor = Author.builder().id("1").name("testAuthor").build();
        Genre testGenre = Genre.builder().id("1").name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        Book savedBook = bookRepository.save(testBook);
        String bookId = savedBook.getId();

        assertThat(bookRepository.findById(bookId)).isNotNull();

        bookRepository.deleteById(bookId);

        assertThat(bookRepository.findById(bookId)).isEmpty();

    }
}