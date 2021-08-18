package ru.otus.homework17.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework17.entity.Book;
import ru.otus.homework17.service.AuthorService;
import ru.otus.homework17.service.BookService;
import ru.otus.homework17.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookPagesController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String getAllBooks(final Model model) {
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }
}