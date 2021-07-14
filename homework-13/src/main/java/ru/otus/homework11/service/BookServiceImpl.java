package ru.otus.homework11.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework11.entity.Author;
import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Genre;
import ru.otus.homework11.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    public Book save(final Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            log.error("Ошибка сохранения книги: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book createBook(final String name, final String authorId, final String genreId) {
        try {
            Book book = Book.builder()
                    .name(name)
                    .author(getAuthor(authorId))
                    .genre(getGenre(genreId))
                    .build();
            return this.save(book);
        } catch (Exception e) {
            log.error("Ошибка создания книги");
            throw new IllegalArgumentException("Ошибка создания книги");
        }
    }


    private Author getAuthor(String authorId) {
        return authorService.getById(authorId);
    }

    private Genre getGenre(String genreId) {
        return genreService.getById(genreId);
    }
}
