package ru.otus.service.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao questionsDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }
}
