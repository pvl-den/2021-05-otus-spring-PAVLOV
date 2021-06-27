package ru.otus.homework07.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework07.entity.Author;
import ru.otus.homework07.entity.Book;
import ru.otus.homework07.entity.Genre;
import ru.otus.homework07.repository.BookRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public boolean insert(final Book book) {
        try {
            bookRepository.insert(book);
            return true;
        } catch (Exception e) {
            log.error("Ошибка сохранения книги");
            return false;
        }
    }

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Boolean createBook(final String name, final long authorId, final long genreId) {

        final Author author = getAuthor(authorId);
        final Genre genre = getGenre(genreId);

        return insert(Book.builder()
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
