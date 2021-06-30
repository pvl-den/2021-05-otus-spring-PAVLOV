package ru.otus.homework09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Author;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.entity.Genre;
import ru.otus.homework09.repository.BookRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public int count() {
        return bookRepository.count();
    }

    @Override
    public Book save(final Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            log.error("Ошибка сохранения книги");
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByName(String name) {
        return bookRepository.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional
    public int deleteById(long id) {
        return bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book createBook(final String name, final long authorId, final long genreId) {

        final Author author = getAuthor(authorId);
        final Genre genre = getGenre(genreId);

        return save(Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build());
    }

    private Author getAuthor(long authorId) {
        try {
            return authorService.getById(authorId);
        } catch (Exception e) {
            log.error("Ошибка получения автора с id: {}", authorId);
            throw new IllegalArgumentException("Ошибка получения автора с id: " + authorId);
        }
    }

    private Genre getGenre(long genreId) {
        try {
            return genreService.getById(genreId);
        } catch (Exception e) {
            log.error("Ошибка получения жанра с id: {}", genreId);
            throw new IllegalArgumentException("Ошибка получения жанра с id: " + genreId);
        }
    }
}
