package ru.otus.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Question {

    private String question;

    private List<String> answer;

}
