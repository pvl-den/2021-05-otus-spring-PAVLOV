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
import static org.junit.jupiter.api.Assertions.*;

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

        assertThat(notesRepository.getById(savedNotes.getId())).isNotNull();

        notesRepository.deleteById(savedNotes.getId());

        assertThat(notesRepository.getById(savedNotes.getId())).isNull();
    }

    @Test
    void deleteByBookId() {

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(book)
                .build();

        Note savedNote = notesRepository.save(expectedNote);

        long bookId = savedNote.getBook().getId();
        List<Note> collect1 = notesRepository.getAll().stream().filter(note -> note.getBook().getId() == bookId).collect(Collectors.toList());

        assertFalse(collect1.isEmpty());

        notesRepository.deleteByBookId(book.getId());
        List<Note> collect2 = notesRepository.getAll().stream().filter(note -> note.getBook().getId() == bookId).collect(Collectors.toList());

        assertTrue(collect2.isEmpty());
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(1, "nameGenre"))
                .author(new Author(1, "nameAuthor"))
                .build();
    }
}