package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


@SpringBootTest
@ActiveProfiles("test")
class ReadFromFileTest {

    @Autowired
    public ReadFromFile readFromFile;

    @Test
    public void testReadFromFile() {
        List<Question> questions = readFromFile.readQuestionsFromFile("questions-test_en.csv");

        assertThat(questions)
                .isNotNull().hasSize(5);
    }

    @Test
    public void testReadFromFileFail() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            readFromFile.readQuestionsFromFile("");
        }).withMessage("incorrect file name");
    }

}