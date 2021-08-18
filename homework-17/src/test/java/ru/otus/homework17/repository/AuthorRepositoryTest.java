package ru.otus.homework17.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework17.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
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

        List<Author> allAuthors = authorRepository.findAll();
        List<String> namesAuthors = allAuthors.stream().map(Author::getName).collect(Collectors.toList());

        assertTrue(namesAuthors.contains(expectedAuthor.getName()));
    }

    @Test
    void getByIdAuthorTest() {
        Author expectedAuthor = Author.builder().name("TestAuthor").build();
        authorRepository.save(expectedAuthor);

        Author actualAuthor = authorRepository.getById(3L);

        assertThat(actualAuthor.getName()).isEqualTo(
                expectedAuthor.getName()
        );
    }

    @Test
    void getAllAuthorTest() {
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors.size()).isEqualTo(2);
    }

    @Test
    void deleteByIdAuthorTest() {
        Author authorForDeleteTest = Author.builder().name("AutorForDeleteTest").build();

        Author savedAuthor = authorRepository.save(authorForDeleteTest);

        long id = savedAuthor.getId();

        assertTrue(authorRepository.findById(id).isPresent());

        authorRepository.deleteById(id);

        assertFalse(authorRepository.findById(id).isPresent());
    }
}