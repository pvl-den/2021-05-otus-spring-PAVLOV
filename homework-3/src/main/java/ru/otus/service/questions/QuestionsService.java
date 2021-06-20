package ru.otus.service.questions;

import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionsService {

    List<Question> getAllQuestions(Locale locale);
}
