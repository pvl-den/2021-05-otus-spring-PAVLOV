package ru.otus.service.quiz;

import ru.otus.domain.Question;
import ru.otus.domain.User;

import java.util.List;

public interface ResultService {

    void outputResultQuiz(final boolean isPassing, final User user);

    boolean getResult(final List<Question> allQuestions, Double resultQuiz);

}
