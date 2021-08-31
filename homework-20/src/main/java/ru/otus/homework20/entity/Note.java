package ru.otus.homework20.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private String noteText;

    private Date noteDate;

    private String noteAuthor;

}