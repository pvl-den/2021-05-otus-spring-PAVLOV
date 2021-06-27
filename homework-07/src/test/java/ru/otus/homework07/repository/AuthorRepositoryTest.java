package ru.otus.homework07.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework07.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@JdbcTest
@Import(AuthorRepositoryJdbc.class)
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldReturnExpectedAuthorCount() {
        int actualAuthorsCount = authorRepository.count();
        assertThat(actualAuthorsCount).isEqualTo(2);
    }

    @Test
    void insertAuthorTest() {
        Author expectedAuthor = Author.builder().name("Джордж Оруэлл").build();
        authorRepository.insert(expectedAuthor);

        List<Author> allAuthors = authorRepository.getAll();
        List<String> namesAuthors = allAuthors.stream().map(Author::getName).collect(Collectors.toList());

        assertTrue(namesAuthors.contains(expectedAuthor.getName()));
    }

    @Test
    void getByIdAuthorTest() {
        Author expectedAuthor = Author.builder().name("TestAuthor").build();
        authorRepository.insert(expectedAuthor);

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
        authorRepository.insert(authorForDeleteTest);

        long authorId = authorRepository.getByName(authorForDeleteTest.getName()).getId();

        assertThatCode(() -> authorRepository.getById(authorId))
                .doesNotThrowAnyException();

        authorRepository.deleteById(authorId);

        assertThatThrownBy(() -> authorRepository.getById(authorId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}