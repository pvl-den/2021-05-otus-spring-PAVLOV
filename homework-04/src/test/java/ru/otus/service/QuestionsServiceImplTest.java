package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;
import ru.otus.service.questions.QuestionsServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class QuestionsServiceImplTest {

    private final Locale locale = Locale.getDefault();

    @MockBean
    private QuestionsDao questionsDao;

    @Autowired
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

        given(questionsDao.getAllQuestions(locale)).willReturn(questions);

        assertThat(questionsService.getAllQuestions(locale))
                .isNotNull()
                .isEqualTo(questions);
    }
}