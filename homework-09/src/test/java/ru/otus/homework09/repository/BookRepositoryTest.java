package ru.otus.homework09.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Author;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.entity.Genre;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
@DataJpaTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void countBookTest() {
        long countBooks = bookRepository.count();
        assertThat(countBooks).isEqualTo(4);
    }

    @Test
    void insertBookTest() {
        Author testAuthor = Author.builder().name("testAuthor").build();
        Genre testGenre = Genre.builder().name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        Book save = bookRepository.save(testBook);

        List<String> collectNames = bookRepository.getAll().stream().map(Book::getName).collect(Collectors.toList());

        assertTrue(collectNames.contains(testBook.getName()));
    }

    @Test
    void getById() {

        Author testAuthor = Author.builder().name("testAuthor").build();
        Genre testGenre = Genre.builder().name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        bookRepository.save(testBook);
        long bookId = bookRepository.getByName("testBookForInsertTest").getId();

        assertThat(bookRepository.getById(bookId).getName()).isEqualTo(testBook.getName());
    }

    @Test
    void getAll() {
        List<Book> allBooks = bookRepository.getAll();
        assertThat(allBooks.size()).isEqualTo(4);
    }

    @Test
    void deleteById() {

        Author testAuthor = Author.builder().name("testAuthor").build();
        Genre testGenre = Genre.builder().name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        bookRepository.save(testBook);
        long bookId = bookRepository.getByName("testBookForInsertTest").getId();

        assertThatCode(() -> bookRepository.getById(bookId))
                .doesNotThrowAnyException();

        bookRepository.deleteById(bookId);

        assertThatThrownBy(() -> bookRepository.getById(bookId))
                .isInstanceOf(NoResultException.class);
    }
}