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
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book createBook(final String name, final long authorId, final long genreId) {
        try {
            Book book = Book.builder()
                    .name(name)
                    .author(getAuthor(authorId))
                    .genre(getGenre(genreId))
                    .build();

            return save(book);
        } catch (Exception e) {
            log.error("Ошибка создания книги");
            throw new IllegalArgumentException("Ошибка создания книги");
        }
    }

    private Author getAuthor(long authorId) {
        return authorService.getById(authorId);
    }

    private Genre getGenre(long genreId) {
        return genreService.getById(genreId);
    }
}
