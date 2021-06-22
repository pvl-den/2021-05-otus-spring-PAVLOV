package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Question;
import ru.otus.service.quiz.ResultService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class ResultServiceImplTest {

    @Autowired
    private ResultService resultService;

    private List<Question> questionsWithCorrectAnswers;
    private List<Question> questionsWithInCorrectAnswers;
    Double resultQuiz;

    @BeforeEach
    public void setUp() {
        questionsWithCorrectAnswers = getQuestionsWithCorrectAnswers();
        questionsWithInCorrectAnswers = getQuestionsWithInCorrectAnswers();
        resultQuiz = 0.5;
    }

    @Test
    void TestGetResultSucceess() {
        boolean result = resultService.getResult(questionsWithCorrectAnswers, resultQuiz);
        assertTrue(result);
    }

    @Test
    void TestGetResultFail() {
        boolean result = resultService.getResult(questionsWithInCorrectAnswers, resultQuiz);
        assertFalse(result);
    }

    private List<Question> getQuestionsWithInCorrectAnswers() {
        List<Question> questions = new ArrayList<>();

        questions.add(Question.builder().question("questionTest" + 1).correctAnswer(Collections.singletonList("answerTest" + 1)).userAnswer("answerTest" + 5).build());
        questions.add(Question.builder().question("questionTest" + 2).correctAnswer(Collections.singletonList("answerTest" + 2)).userAnswer("answerTest" + 4).build());
        questions.add(Question.builder().question("questionTest" + 3).correctAnswer(Collections.singletonList("answerTest" + 3)).userAnswer("answerTest" + 3).build());
        questions.add(Question.builder().question("questionTest" + 4).correctAnswer(Collections.singletonList("answerTest" + 4)).userAnswer("answerTest" + 2).build());
        questions.add(Question.builder().question("questionTest" + 5).correctAnswer(Collections.singletonList("answerTest" + 5)).userAnswer("answerTest" + 1).build());
        return questions;
    }

    private List<Question> getQuestionsWithCorrectAnswers() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            questions.add(Question.builder().question("questionTest" + i).correctAnswer(Arrays.asList("answerTest" + i)).userAnswer("answerTest" + i).build());
        }
        return questions;
    }

}