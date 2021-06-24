package ru.otus.homework07.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;
    private String name;
    private Genre genre;
    private Author author;

}
