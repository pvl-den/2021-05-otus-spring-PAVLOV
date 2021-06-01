package ru.otus.service.questions;

import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;

import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao questionsDao;

    public QuestionsServiceImpl(final QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }
}
