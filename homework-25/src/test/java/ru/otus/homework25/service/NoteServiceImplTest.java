package ru.otus.homework25.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework25.entity.Author;
import ru.otus.homework25.entity.Book;
import ru.otus.homework25.entity.Genre;
import ru.otus.homework25.entity.Note;
import ru.otus.homework25.repository.BookRepository;
import ru.otus.homework25.service.note.NoteService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoteServiceImplTest {

    @Autowired
    NoteService noteService;

    @Autowired
    BookRepository bookRepository;

    @Test
    void getByBookId() throws ParseException {

        Book book = getBook();

        Book savedBook = bookRepository.save(book);

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-07"))
                .book(savedBook)
                .build();

        Note savedNote = noteService.save(expectedNote);

        List<Note> notes = savedNote.getBook().getNotes();

        assertThat(savedNote).isIn(notes);
    }

    private Book getBook() {
        return Book.builder()
                .name("nameBook")
                .genre(new Genre(1, "nameGenre"))
                .author(new Author(1, "nameAuthor"))
                .notes(new ArrayList<>())
                .build();
    }
}