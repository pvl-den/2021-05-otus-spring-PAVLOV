package ru.otus.homework13.service;

import ru.otus.homework13.entity.Note;

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
