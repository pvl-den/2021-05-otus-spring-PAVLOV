package ru.otus.homework09.repository;

import ru.otus.homework09.entity.Note;

import java.util.List;

public interface NotesRepository {

    Note save(Note note);

    Note getById(long id);

    List<Note> getAll();

    void deleteById(long id);

    int deleteByBookId(long bookId);
}
