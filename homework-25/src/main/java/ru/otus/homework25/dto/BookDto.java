package ru.otus.homework25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework25.entity.Book;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id = -1;

    private String name;

    private String genre;

    private String author;

    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .author(book.getAuthor().getName())
                .build();
    }
}
