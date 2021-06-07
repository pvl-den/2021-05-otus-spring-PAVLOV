package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;
import ru.otus.service.questions.QuestionsServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    public void getAllQuestions() {
        List<Question> questions = Collections.singletonList(Question.builder()
                .question("testQuestion")
                .correctAnswer(Collections.singletonList("testAnswer"))
                .build());

        given(questionsDao.getAllQuestions()).willReturn(questions);
        assertThat(questionsService.getAllQuestions())
                .isNotNull()
                .isEqualTo(questions);
    }
}