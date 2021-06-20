package ru.otus.dao;

import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionsDao {

    List<Question> getAllQuestions(Locale locale);
}
