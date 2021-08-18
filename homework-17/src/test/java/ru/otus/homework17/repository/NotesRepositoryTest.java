package ru.otus.homework17.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework17.entity.Author;
import ru.otus.homework17.entity.Book;
import ru.otus.homework17.entity.Genre;
import ru.otus.homework17.entity.Note;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
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

        List<Note> allNote = notesRepository.findAll();
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
        List<Note> all = notesRepository.findAll();
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

        assertThat(notesRepository.findById(savedNotes.getId())).isNotNull();

        notesRepository.deleteById(savedNotes.getId());

        assertThat(notesRepository.findById(savedNotes.getId())).isEmpty();
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

        List<Note> notesBeforDelete = notesRepository.findAll();

        assertThat(notesBeforDelete.size()).isEqualTo(3);

        notesRepository.deleteByBookId(book.getId());

        List<Note> notesAfterDelete = notesRepository.findAll();
        assertThat(notesAfterDelete.size()).isEqualTo(2);
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(1, "nameGenre"))
                .author(new Author(1, "nameAuthor"))
                .build();
    }
}