package ru.otus.service.questions;

import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

public interface CreatingQuestions {

    List<Question> createQuestionsFromFile(final Locale locale);
}
