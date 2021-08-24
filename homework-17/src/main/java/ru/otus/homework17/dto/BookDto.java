package ru.otus.homework17.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework17.entity.Author;
import ru.otus.homework17.entity.Book;
import ru.otus.homework17.entity.Genre;
import ru.otus.homework17.entity.Note;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id = -1;

    private String name;

    private String genre;

    private String author;

    public static BookDto toDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .author(book.getAuthor().getName())
                .build();
    }
}
