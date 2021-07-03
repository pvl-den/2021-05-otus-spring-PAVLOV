package ru.otus.homework09.service;

import ru.otus.homework09.entity.Note;

import java.util.List;

public interface NoteService {

    Note save(Note note);

    Note getById(long id);

    List<Note> getByBookId(long bookId);

    List<Note> getAll();

    int deleteById(long id);

    int deleteByBookId(long bookId);

}
