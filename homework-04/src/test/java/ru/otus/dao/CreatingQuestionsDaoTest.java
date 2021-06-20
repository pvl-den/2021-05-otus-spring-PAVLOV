package ru.otus.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;
import ru.otus.service.questions.CreatingQuestions;
import ru.otus.service.questions.CreatingQuestionsServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class CreatingQuestionsDaoTest {

    @Autowired
    private CreatingQuestions creatingQuestions;

    @MockBean
    private QuestionsDao questionsDao;

    @MockBean
    private QuizConfig quizConfig;

    private Locale locale;

    @BeforeEach
    void setUp() {
        creatingQuestions = new CreatingQuestionsServiceImpl(questionsDao, quizConfig);
        locale = Locale.ENGLISH;
    }

    @Test
    public void testGetQuestions() throws IOException {
        given(quizConfig.getFileName()).willReturn("q-test");
        given(quizConfig.getFileType()).willReturn("cs");
        given(quizConfig.getAvailableLocales()).willReturn(new String[]{"ru", "en"});

        String fileNameByLocale = getFileNameByLocale(locale);

        given(questionsDao.readFromFile(fileNameByLocale)).willReturn(getArrayQuestions());


        List<Question> questions = creatingQuestions.createQuestionsFromFile(locale);
        Assertions.assertThat(questions).isNotNull().hasSize(5);

        String question = questions.stream().findAny().get().getQuestion();
        assertThat(question).isIn("testQuestions1", "testQuestions2", "testQuestions3", "testQuestions4", "testQuestions5");
    }


    private String getFileNameByLocale(final Locale locale) {
        return String.format("%s_%s.%s", quizConfig.getFileName(), locale.getLanguage(), quizConfig.getFileType());
    }


    private List<String[]> getArrayQuestions() {

        List<String[]> stringList = new ArrayList<>();

        String[] line1 = {"testQuestions1", "testAnswer1"};
        String[] line2 = {"testQuestions2", "testAnswer2"};
        String[] line3 = {"testQuestions3", "testAnswer3"};
        String[] line4 = {"testQuestions4", "testAnswer4"};
        String[] line5 = {"testQuestions5", "testAnswer5"};

        stringList.add(line1);
        stringList.add(line2);
        stringList.add(line3);
        stringList.add(line4);
        stringList.add(line5);

        return stringList;
    }

}