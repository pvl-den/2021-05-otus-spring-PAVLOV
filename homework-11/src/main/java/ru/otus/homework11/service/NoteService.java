package ru.otus.homework11.service;

import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);

    Note getById(long id);

    List<Note> getByBookId(long bookId);

    List<Note> getAll();

    void deleteById(long id);

    void deleteByBookId(long bookId);

    Note createNote(final String noteText, final String noteAuthor, final long bookId);

}
