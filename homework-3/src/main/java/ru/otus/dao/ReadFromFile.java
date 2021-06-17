package ru.otus.dao;

import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ReadFromFile {

    List<Question> readQuestionsFromFile(final String fileName);

    InputStream getInputStream(String fileName) throws IOException;

    List<Question> getQuestions(final List<String[]> stringList);

}
