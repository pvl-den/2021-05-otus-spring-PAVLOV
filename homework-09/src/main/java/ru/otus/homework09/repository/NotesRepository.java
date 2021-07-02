package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Note;

import java.util.List;

public interface NotesRepository {

    Note save(Note note);

    Note getById(long id);

    Note getByBookId(long bookId);

    List<Note> getAll();

    int deleteById(long id);

    int deleteByBookId(long bookId);
}
