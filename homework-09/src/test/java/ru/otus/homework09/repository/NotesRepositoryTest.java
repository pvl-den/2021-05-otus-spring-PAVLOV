package ru.otus.homework09.repository;

import org.junit.jupiter.api.BeforeEach;
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
@Import({NotesRepositoryJpa.class, BookRepositoryJpa.class})
@Transactional
class NotesRepositoryTest {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    BookRepository bookRepository;

    Book book;

    @BeforeEach
    void setUp() {
        book = bookRepository.save(getBook());
    }

    @Test
    void save() {
        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(book)
                .build();

        Note savedNote = notesRepository.save(expectedNote);

        List<Note> allNote = notesRepository.getAll();
        List<String> namesNotes = allNote.stream().map(Note::getNoteAuthor).collect(Collectors.toList());

        assertTrue(namesNotes.contains(expectedNote.getNoteAuthor()));
        assertEquals(expectedNote.getNoteAuthor(), savedNote.getNoteAuthor());
        assertEquals(expectedNote.getNoteText(), savedNote.getNoteText());

    }

    @Test
    void getById() {

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(book)
                .build();

        Note savedNotes = notesRepository.save(expectedNote);

        assertThat(notesRepository.getById(savedNotes.getId()).getNoteText()).isEqualTo(expectedNote.getNoteText());
    }


    @Test
    void getByBookId() {

        Note expectedNote1 = Note.builder()
                .noteAuthor("testAuyhor1")
                .noteText("noteText1")
                .noteDate(new Date())
                .book(book)
                .build();

        Note expectedNote2 = Note.builder()
                .noteAuthor("testAuyhor2")
                .noteText("noteText2")
                .noteDate(new Date())
                .book(book)
                .build();

        Note savedNotes1 = notesRepository.save(expectedNote1);
        Note savedNotes2 = notesRepository.save(expectedNote2);

        List<Note> byBookId = notesRepository.getByBookId(book.getId());

        assertThat(notesRepository.getByBookId(savedNotes1.getBook().getId())).hasSize(2);
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
                .book(book)
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
                .book(book)
                .build();

        notesRepository.save(expectedNote);

        assertThatCode(() -> notesRepository.getByBookId(book.getId()))
                .doesNotThrowAnyException();

        notesRepository.deleteByBookId(book.getId());

        assertTrue(notesRepository.getByBookId(book.getId()).isEmpty());
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(1, "nameGenre"))
                .author(new Author(1, "nameAuthor"))
                .build();
    }
}