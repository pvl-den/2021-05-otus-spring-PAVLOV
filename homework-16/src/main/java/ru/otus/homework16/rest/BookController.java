package ru.otus.homework16.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework16.entity.Book;
import ru.otus.homework16.service.AuthorService;
import ru.otus.homework16.service.BookService;
import ru.otus.homework16.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String getAllBooks(final Model model) {
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long bookId, final Model model) {
        final Book book = bookService.getById(bookId);
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("/edit")
    public String saveBook(final String bookName, String genreName, String authorName, final Model model) {

        long genreId = genreService.getByName(genreName).getId();
        long authorId = authorService.getByName(authorName).getId();

        Book book = bookService.createBook(bookName, authorId, genreId);
        model.addAttribute("book", book);
        return "editbook";
    }

}