package ru.otus.homework25.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework25.controller.error.DeniedHandler;
import ru.otus.homework25.entity.Author;
import ru.otus.homework25.entity.Book;
import ru.otus.homework25.entity.Genre;
import ru.otus.homework25.security.SecurityConfiguration;
import ru.otus.homework25.service.author.AuthorService;
import ru.otus.homework25.service.book.BookService;
import ru.otus.homework25.service.genre.GenreService;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(BookController.class)
@ContextConfiguration(classes = {DeniedHandler.class, SecurityConfiguration.class})
class BookControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    GenreService genreService;

    @MockBean
    AuthorService authorService;

    @BeforeEach
    public void setup() {
        Author testAuthor_1 = Author.builder().id(1).name("testAuthor_1").build();
        Genre testGenre_1 = Genre.builder().id(1).name("testGenre_1").build();
        given(authorService.getByName("testAuthor_1")).willReturn(testAuthor_1);
        given(genreService.getByName("testGenre_1")).willReturn(testGenre_1);
        given(bookService.createBook("book", 1, 1)).willReturn(getBook());

        given(bookService.getAll()).willReturn(Collections.singletonList(getBook()));

    }

    @WithMockUser(
            username = "user"
    )
    @Test
    void allBooksTest() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk());
    }


    @WithMockUser(
            username = "admin",
            password = "admin",
            roles = "ADMIN"
    )
    @Test
    @DisplayName("сохранение книги авторизованным пользователем")
    void saveBooksTest() throws Exception {
        mockMvc.perform(post("/book/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("bookName=book&genreName=testGenre_1&authorName=testAuthor_1"))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("сохранение книги без авторизации. Редерикт на форму ввода логина")
    void saveBooksTestFail() throws Exception {
        mockMvc.perform(post("/book/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("bookName=book&genreName=testGenre_1&authorName=testAuthor_1"))
                .andExpect(status().is3xxRedirection());
    }

    private Book getBook() {
        return Book.builder()
                .id(1)
                .name("nameBook")
                .genre(new Genre(0, "nameGenre"))
                .author(new Author(0, "nameAuthor"))
                .build();
    }
}