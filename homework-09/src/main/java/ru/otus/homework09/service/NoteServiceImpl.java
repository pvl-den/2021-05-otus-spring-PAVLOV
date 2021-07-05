package ru.otus.homework09.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Note;
import ru.otus.homework09.repository.BookRepository;
import ru.otus.homework09.repository.NotesRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NotesRepository notesRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Note save(final Note note) {
        return notesRepository.save(note);
    }

    @Override
    @Transactional(readOnly = true)
    public Note getById(final long id) {
        return notesRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getByBookId(final long bookId) {
        return this.getAll().stream().filter(note -> note.getBook().getId() == bookId).collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAll() {
        return notesRepository.getAll();
    }

    @Override
    @Transactional
    public void deleteById(final long id) {
        notesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByBookId(final long bookId) {
        return notesRepository.deleteByBookId(bookId);
    }
}
