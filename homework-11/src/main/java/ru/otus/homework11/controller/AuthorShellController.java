package ru.otus.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework11.entity.Author;
import ru.otus.homework11.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellController {

    private final AuthorService authorService;


    @ShellMethod(value = "Получить всех авторов.", key = {"get-authors", "ga"})
    public String getAuthors() {

        return buildResultllAuthors(authorService.getAll());
    }

    @ShellMethod(value = "Добавить автора с именем name.", key = {"insert-author", "ia"})
    public String insertAuthor(final String firstName, final String lastName) {

        final String newAuthor = String.format("%s %s", firstName, lastName);
        authorService.save(Author.builder().name(newAuthor).build());

        return String.format("Автор %s добавлен", newAuthor);
    }

    @ShellMethod(value = "Удаление автора по id.", key = {"delete-author", "da"})
    public String deleteAuthor(final long id) {
        authorService.deleteById(id);
        return String.format("Автор c id=%s удален", id);
    }

    private String buildResultllAuthors(List<Author> allAuthors) {
        return "Список авторов: \n" + allAuthors.stream().map(Author::getName).collect(Collectors.joining("\n"));
    }

}