package ru.otus.homework16.rest;

import ru.otus.homework16.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AuthorController {

    private final AuthorService authorService;


}