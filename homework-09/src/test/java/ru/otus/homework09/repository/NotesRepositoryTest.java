package ru.otus.homework09.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Author;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.entity.Genre;
import ru.otus.homework09.entity.Note;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(NotesRepositoryJpa.class)
@Transactional
class NotesRepositoryTest {

    @Autowired
    NotesRepository notesRepository;

    @Test
    void save() {
        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(getBook())
                .build();

        Note savedNote = notesRepository.save(expectedNote);

        List<Note> allNote = notesRepository.getAll();
        List<String> namesNotes = allNote.stream().map(Note::getNoteAuthor).collect(Collectors.toList());

        assertTrue(namesNotes.contains(expectedNote.getNoteAuthor()));
        assertEquals(expectedNote, savedNote);

    }

    @Test
    void getById() {

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(getBook())
                .build();

        Note savedNotes = notesRepository.save(expectedNote);

        assertThat(notesRepository.getById(savedNotes.getId())).isEqualTo(expectedNote);
    }


    @Test
    void getByBookId() {

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(getBook())
                .build();

        Note savedNotes = notesRepository.save(expectedNote);

        assertThat(notesRepository.getByBookId(savedNotes.getBook().getId())).isEqualTo(expectedNote);
    }

    @Test
    void getAll() {
        List<Note> all = notesRepository.getAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void deleteById() {
        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(getBook())
                .build();

        Note savedNotes = notesRepository.save(expectedNote);

        assertThatCode(() -> notesRepository.getById(savedNotes.getId()))
                .doesNotThrowAnyException();

        notesRepository.deleteById(savedNotes.getId());

        assertThatThrownBy(() -> notesRepository.getById(savedNotes.getId()))
                .isInstanceOf(NoResultException.class);

    }

    @Test
    void deleteByBookId() {

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(getBook())
                .build();

        Note savedNotes = notesRepository.save(expectedNote);
        Book book = savedNotes.getBook();

        assertThatCode(() -> notesRepository.getByBookId(book.getId()))
                .doesNotThrowAnyException();

        notesRepository.deleteByBookId(book.getId());

        assertThatThrownBy(() -> notesRepository.getByBookId(book.getId()))
                .isInstanceOf(NoResultException.class);
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(0, "nameGenre"))
                .author(new Author(0, "nameAuthor"))
                .build();
    }
}