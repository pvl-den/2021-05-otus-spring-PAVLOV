package ru.otus.homework16.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework16.entity.Book;
import ru.otus.homework16.entity.Note;
import ru.otus.homework16.repository.BookRepository;
import ru.otus.homework16.repository.NotesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NotesRepository notesRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Note save(final Note note) {
        try {
            Note savedNote = notesRepository.save(note);
            if (checkNotes(savedNote)) {
                savedNote.getBook().setNotes(new ArrayList<>());
            }
            savedNote.getBook().getNotes().add(note);
            return savedNote;
        } catch (Exception e) {
            log.error("Ошибка сохранения комментария: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Note getById(final long id) {
        return notesRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getByBookId(final long bookId) {
        final Book book = bookRepository.getById(bookId);
        return book.getNotes();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAll() {
        return notesRepository.findAll();
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

    @Override
    public Note createNote(final String noteText, final String noteAuthor, final long bookId) {
        final Book book = getBook(bookId);
        if (book == null) {
            log.error("Книга с id {} не существует", bookId);
            throw new IllegalArgumentException("Ошибка создания комментария к книге");
        }
        final Note note = getNote(noteText, noteAuthor, book);
        return save(note);
    }

    private Note getNote(String noteText, String noteAuthor, Book book) {
        return Note.builder()
                .noteText(noteText)
                .book(book)
                .noteAuthor(noteAuthor)
                .noteDate(new Date())
                .build();
    }

    private boolean checkNotes(final Note savedNote) {
        return savedNote.getBook().getNotes() == null || savedNote.getBook().getNotes().isEmpty();
    }

    private Book getBook(final long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
}
