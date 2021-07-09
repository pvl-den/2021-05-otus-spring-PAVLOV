package ru.otus.homework11.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework11.entity.Author;
import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
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
        Author testAuthor = Author.builder().id(1).name("testAuthor").build();
        Genre testGenre = Genre.builder().id(1).name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        Book save = bookRepository.save(testBook);

        List<String> collectNames = bookRepository.findAll().stream().map(Book::getName).collect(Collectors.toList());

        assertTrue(collectNames.contains(testBook.getName()));
    }

    @Test
    void getById() {

        Author testAuthor = Author.builder().id(1).name("testAuthor").build();
        Genre testGenre = Genre.builder().id(1).name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        bookRepository.save(testBook);
        long bookId = bookRepository.findByName("testBookForInsertTest").getId();

        assertThat(bookRepository.getById(bookId).getName()).isEqualTo(testBook.getName());
    }

    @Test
    void getAll() {
        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks.size()).isEqualTo(4);
    }

    @Test
    void deleteById() {

        Author testAuthor = Author.builder().id(1).name("testAuthor").build();
        Genre testGenre = Genre.builder().id(1).name("testGenre").build();
        Book testBook = Book.builder().name("testBookForInsertTest").author(testAuthor).genre(testGenre).build();

        Book savedBook = bookRepository.save(testBook);
        long bookId = savedBook.getId();

        assertThat(bookRepository.findById(bookId)).isNotNull();

        bookRepository.deleteById(bookId);

        assertThat(bookRepository.findById(bookId)).isNull();

    }

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        var books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(4)
                .allMatch(book -> !book.getName().equals(""))
                .allMatch(book -> book.getAuthor() != null)
                .allMatch(book -> book.getGenre() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }
}