package ru.otus.dao;

import ru.otus.domain.Question;

import java.util.List;

public interface ReadFromFile {

    public List<Question> readQuestionsFromFile(final String fileName);

}
