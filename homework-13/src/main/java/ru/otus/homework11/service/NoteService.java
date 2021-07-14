package ru.otus.homework11.service;

import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);

    Note getById(String id);

    List<Note> getByBookId(String bookId);

    List<Note> getAll();

    void deleteById(String id);

    void deleteByBookId(String bookId);

    Note createNote(final String noteText, final String noteAuthor, final String bookId);

}
