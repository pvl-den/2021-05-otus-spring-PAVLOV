package ru.otus.homework25.service.book;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.homework25.entity.Book;

import java.util.List;

public interface BookService {

    long count();

    Book save(Book book);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(long id);

    Book createBook(String name, long authorId, long genreId);
}
