package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.domain.Question;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionsDaoImplTest {

    @Test
    void getAllQuestions() {
        QuestionsDao questionsDao = new QuestionsDaoImpl("questionsTest.csv");
        List<Question> allQuestions = questionsDao.getAllQuestions();

        assertThat(allQuestions).isNotNull();
        assertThat(allQuestions.size()).isEqualTo(5);
        assertThat(allQuestions.stream().map(Question::getQuestion).findAny().get()).isNotNull();
    }
}