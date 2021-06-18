package ru.otus.service.quiz;

import ru.otus.domain.Question;

import java.util.List;

public interface QuizService {

    void startQuiz();

    void outputResult(final boolean isPassing);

    boolean getResult(final List<Question> allQuestions);

    }
