package ru.otus.homework09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework09.entity.Book;
import ru.otus.homework09.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookShellController {

    private final BookService bookService;

    @ShellMethod(value = "Получить количество книг.", key = {"count-books", "cb"})
    public String getCountBooks() {
        return "Всего книг в базе: " + bookService.count();
    }

    @ShellMethod(value = "Получить все книги.", key = {"get-books", "gb"})
    public String getBooks() {
        return bookService.getAll().toString();
    }

    @ShellMethod(value = "Получить книгу по id.", key = {"get-book-by-id", "gb-id"})
    public String getBook(final long id) {
        return bookService.getById(id).toString();
    }

    /**
     * @param name     название книги
     * @param authorId id автора
     * @param genreId  id жанра
     * @return название добавленной книги
     */
    @ShellMethod(value = "Добавить книгу", key = {"add-book", "ab"})
    public String addBook(final String name, final long authorId, final long genreId) {
        final Book book = bookService.createBook(name, authorId, genreId);
        return book != null ? "Книга с названием '" + name + "' добавлена"
                : "Ошибка добавления книги";
    }


    @ShellMethod(value = "Удаление книги по id.", key = {"delete-book", "db"})
    public String deleteBook(final long id) {
        bookService.deleteById(id);
        return String.format("Книга c id=%s удалена", id);
    }

}