package ru.otus.homework11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework11.entity.Author;
import ru.otus.homework11.entity.Book;
import ru.otus.homework11.entity.Genre;
import ru.otus.homework11.repository.AuthorRepository;
import ru.otus.homework11.repository.BookRepository;
import ru.otus.homework11.repository.GenreRepository;

import java.util.ArrayList;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "PavlovDV", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "add-authors", author = "PavlovDV")
    public void addAuthor(AuthorRepository authorRepository) {
        authorRepository.deleteAll();
        authorRepository.save(new Author("1", "Джеймс Клавелл"));
        authorRepository.save(new Author("2", "Эрих Мария Ремарк"));
    }

    @ChangeSet(order = "003", id = "add-genres", author = "PavlovDV")
    public void addGenre(GenreRepository genreRepository) {
        genreRepository.deleteAll();
        genreRepository.save(new Genre("1", "Приключения"));
        genreRepository.save(new Genre("2", "Роман"));
    }

    @ChangeSet(order = "004", id = "add-books", author = "PavlovDV")
    public void addBook(BookRepository bookRepository) {
        bookRepository.deleteAll();
        bookRepository.save(new Book("1", "Сегун", new Genre("1", "Приключения"), new Author("1", "Джеймс Клавелл"), new ArrayList<>()));
        bookRepository.save(new Book("2", "Триумфальная арк", new Genre("2", "Роман"), new Author("1", "Эрих Мария Ремарк"), new ArrayList<>()));
    }

}
