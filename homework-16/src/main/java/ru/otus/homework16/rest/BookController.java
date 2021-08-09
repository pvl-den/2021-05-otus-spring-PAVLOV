package ru.otus.homework16.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.homework16.entity.Book;
import ru.otus.homework16.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String getAllBooks(final Model model){
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }

    public String createBook(final Book book, final Model model){
        Book savedBook = bookService.save(book);
        model.addAttribute("book", savedBook);
        return "addbook";
    }

}