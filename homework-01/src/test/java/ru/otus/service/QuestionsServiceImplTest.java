package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class QuestionsServiceImplTest {

    @Mock
    private QuestionsDao questionsDao;

    @InjectMocks
    QuestionsServiceImpl questionsService;

    @BeforeEach
    void setUp() {
        questionsService = new QuestionsServiceImpl(questionsDao);
    }

    @Test
    void getAllQuestions() {
        List<Question> questions = Collections.singletonList(Question.builder()
                .question("testQuestion")
                .answer(Collections.singletonList("testAnswer"))
                .build());

        given(questionsDao.getAllQuestions()).willReturn(questions);
        assertEquals(questionsService.getAllQuestions(), questions);
    }

}