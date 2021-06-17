package ru.otus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


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

    @Test
    public void testGetInputStream() throws IOException {
        InputStream inputStream = readFromFile.getInputStream("questions-test_en.csv");

        assertThat(inputStream)
                .isNotNull();
    }

    @Test
    public void testGetInputStreamIOException() {

        assertThatIOException().isThrownBy(() -> {
            readFromFile.getInputStream("qw");
        });
    }

    @Test
    public void testGetQuestions() {

        List<Question> questions = readFromFile.getQuestions(getArray());
        assertThat(questions).isNotNull().hasSize(5);

        String question = questions.stream().findAny().get().getQuestion();
        assertThat(question).isIn("testQuestions1", "testQuestions2", "testQuestions3", "testQuestions4", "testQuestions5");
    }

    private List<String[]> getArray() {

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