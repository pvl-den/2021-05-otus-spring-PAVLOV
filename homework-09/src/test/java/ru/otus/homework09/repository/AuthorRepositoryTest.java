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

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualAuthorsCount = authorRepository.count();
        assertThat(actualAuthorsCount).isEqualTo(2);
    }

    @Test
    void saveAuthorTest() {
        Author expectedAuthor = Author.builder().name("Джордж Оруэлл").build();
        authorRepository.save(expectedAuthor);

        List<Author> allAuthors = authorRepository.getAll();
        List<String> namesAuthors = allAuthors.stream().map(Author::getName).collect(Collectors.toList());

        assertTrue(namesAuthors.contains(expectedAuthor.getName()));
    }

    @Test
    void getByIdAuthorTest() {
        Author expectedAuthor = Author.builder().name("TestAuthor").build();
        authorRepository.save(expectedAuthor);

        Author actualAuthor = authorRepository.getById(3);

        assertThat(actualAuthor.getName()).isEqualTo(
                expectedAuthor.getName()
        );
    }

    @Test
    void getAllAuthorTest() {
        List<Author> allAuthors = authorRepository.getAll();
        assertThat(allAuthors.size()).isEqualTo(2);
    }

    @Test
    void deleteByIdAuthorTest() {
        Author authorForDeleteTest = Author.builder().name("AutorForDeleteTest").build();
        Author savedAuthor = authorRepository.save(authorForDeleteTest);

        assertThatCode(() -> authorRepository.getById(savedAuthor.getId()))
                .doesNotThrowAnyException();

        authorRepository.deleteById(savedAuthor.getId());

        assertThatThrownBy(() -> authorRepository.getById(savedAuthor.getId()))
                .isInstanceOf(NoResultException.class);
    }
}