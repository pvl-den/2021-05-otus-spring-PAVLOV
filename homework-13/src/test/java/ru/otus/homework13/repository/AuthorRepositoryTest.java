package ru.otus.homework13.repository;

import com.github.cloudyrock.mongock.ChangeLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework13.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ChangeLog
@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualAuthorsCount = authorRepository.count();
        assertThat(actualAuthorsCount).isEqualTo(3);
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
        Author expectedAuthor = Author.builder().id("3").name("TestAuthor").build();
        authorRepository.save(expectedAuthor);

        Author actualAuthor = authorRepository.findById("3").get();

        assertThat(actualAuthor.getName()).isEqualTo(
                expectedAuthor.getName()
        );
    }

    @Test
    void getAllAuthorTest() {
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors.size()).isEqualTo(4);
    }

    @Test
    void deleteByIdAuthorTest() {
        Author authorForDeleteTest = Author.builder().name("AutorForDeleteTest").build();

        Author savedAuthor = authorRepository.save(authorForDeleteTest);

        String id = savedAuthor.getId();

        assertTrue(authorRepository.findById(id).isPresent());

        authorRepository.deleteById(id);

        assertFalse(authorRepository.findById(id).isPresent());
    }
}