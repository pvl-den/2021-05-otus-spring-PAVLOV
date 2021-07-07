package ru.otus.homework09.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.entity.Note;
import ru.otus.homework09.repository.BookRepository;
import ru.otus.homework09.repository.NotesRepository;

import java.util.List;

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
        Book book = bookRepository.getById(bookId);
        return book.getNotes();
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
    public void deleteByBookId(final long bookId) {
        notesRepository.deleteByBookId(bookId);
    }
}
