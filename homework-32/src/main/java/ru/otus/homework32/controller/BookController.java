package ru.otus.homework32.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.homework32.dto.BookDto;
import ru.otus.homework32.entity.Book;
import ru.otus.homework32.service.author.AuthorService;
import ru.otus.homework32.service.book.BookService;
import ru.otus.homework32.service.genre.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/book")
    public String allBooks(final Model model) {
        final List<BookDto> books = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book/edit")
    public String editBook(final Model model) {
        final List<BookDto> books = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "editbook";
    }

    @PostMapping("/book/edit")
    public String saveBook(final String bookName, String genreName, String authorName, final Model model) {

        long genreId = genreService.getByName(genreName).getId();
        long authorId = authorService.getByName(authorName).getId();

        Book book = bookService.createBook(bookName, authorId, genreId);
        model.addAttribute("bookDto", BookDto.toDto(book));
        return "editbook";
    }

    @GetMapping("/book/delete")
    public String deleteBook(final Model model, @RequestParam("id") long id) {
        final Book book = bookService.getById(id);
        if (book == null) return "error/401";
        bookService.deleteById(book.getId());

        final List<BookDto> books = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String error401() {
        return "/error/401";
    }

}