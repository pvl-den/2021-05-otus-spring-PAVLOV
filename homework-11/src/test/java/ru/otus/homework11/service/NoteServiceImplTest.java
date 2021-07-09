package ru.otus.homework11.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework11.entity.Author;
import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Genre;
import ru.otus.homework11.entity.Note;
import ru.otus.homework11.repository.BookRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoteServiceImplTest {

    @Autowired
    NoteService noteService;

    @Autowired
    BookRepository bookRepository;

    @Test
    void getByBookId() {

        Book book = getBook();

        Book savedBook = bookRepository.save(book);

        Note expectedNote = Note.builder()
                .noteAuthor("testAuyhor")
                .noteText("noteText")
                .noteDate(new Date())
                .book(savedBook)
                .build();

        Note savedNote = noteService.save(expectedNote);

        // savedNote.getBook().getNotes().add(savedNote);

        List<Note> notes = savedNote.getBook().getNotes();

        //?? если явно не добавить как в закоментированной строке выше, то notes пустой
        assertThat(notes).isEmpty();
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