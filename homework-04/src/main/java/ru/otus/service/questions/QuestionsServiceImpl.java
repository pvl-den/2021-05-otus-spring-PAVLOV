package ru.otus.service.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    private final CreatingQuestions creatingQuestions;

    @Override
    public List<Question> getAllQuestions(final Locale locale) {
        return creatingQuestions.createQuestionsFromFile(locale);
    }

}
