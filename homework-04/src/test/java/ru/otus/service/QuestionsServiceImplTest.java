package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;
import ru.otus.service.questions.CreatingQuestions;
import ru.otus.service.questions.QuestionsServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@SpringBootTest
public class QuestionsServiceImplTest {

    private final Locale locale = Locale.getDefault();

    @MockBean
    private CreatingQuestions creatingQuestions;

    @Autowired
    QuestionsServiceImpl questionsService;

    @BeforeEach
    void setUp() {
        questionsService = new QuestionsServiceImpl(creatingQuestions);
    }

    @Test
    public void getAllQuestions() {

        List<Question> questions = Collections.singletonList(Question.builder()
                .question("testQuestion")
                .correctAnswer(Collections.singletonList("testAnswer"))
                .build());

        given(creatingQuestions.createQuestionsFromFile(locale)).willReturn(questions);

        assertThat(questionsService.getAllQuestions(locale))
                .isNotNull()
                .isEqualTo(questions);
    }
}