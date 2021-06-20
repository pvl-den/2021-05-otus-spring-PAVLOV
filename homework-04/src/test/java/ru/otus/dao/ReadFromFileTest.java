package ru.otus.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;
import ru.otus.service.questions.CreatingQuestions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class ReadFromFileTest {

    @Autowired
    public QuestionsDao questionsDao;

    @Test
    public void testReadFromFile() throws IOException {

        List<String[]> strings = questionsDao.readFromFile("questions-test_en.csv");

        Assertions.assertThat(strings)
                .isNotNull().hasSize(5);
    }

    @Test
    public void testReadFromFileEmptyFileName() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            questionsDao.readFromFile("");
        }).withMessage("file name is empty");
    }

    @Test
    public void testReadFromFileIncorrectFileName() {
        assertThatIOException ().isThrownBy(() -> {
            questionsDao.readFromFile("incorectFileName");
        }).withMessage("file read error");
    }

}