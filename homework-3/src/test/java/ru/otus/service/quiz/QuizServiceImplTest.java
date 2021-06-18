package ru.otus.service.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class QuizServiceImplTest {

    @Autowired
    private QuizService quizService;

    private List<Question> questionsWithCorrectAnswers;
    private List<Question> questionsWithInCorrectAnswers;

    @BeforeEach
    public void setUp() {
        questionsWithCorrectAnswers = getQuestionsWithCorrectAnswers();
        questionsWithInCorrectAnswers = getQuestionsWithInCorrectAnswers();
    }

    @Test
    void TestGetResultSucceess() {
        boolean result = quizService.getResult(questionsWithCorrectAnswers);
        assertTrue(result);
    }

    @Test
    void TestGetResultFail() {
        boolean result = quizService.getResult(questionsWithInCorrectAnswers);
        assertFalse(result);
    }

    private List<Question> getQuestionsWithInCorrectAnswers() {
        List<Question> questions = new ArrayList<>();

            questions.add(Question.builder().question("questionTest"+ 1).correctAnswer(Collections.singletonList("answerTest" + 1)).userAnswer("answerTest" + 5).build());
            questions.add(Question.builder().question("questionTest"+ 2).correctAnswer(Collections.singletonList("answerTest" + 2)).userAnswer("answerTest" + 4).build());
            questions.add(Question.builder().question("questionTest"+ 3).correctAnswer(Collections.singletonList("answerTest" + 3)).userAnswer("answerTest" + 3).build());
            questions.add(Question.builder().question("questionTest"+ 4).correctAnswer(Collections.singletonList("answerTest" + 4)).userAnswer("answerTest" + 2).build());
            questions.add(Question.builder().question("questionTest"+ 5).correctAnswer(Collections.singletonList("answerTest" + 5)).userAnswer("answerTest" + 1).build());
        return questions;
    }

    private List<Question> getQuestionsWithCorrectAnswers() {
        List<Question> questions = new ArrayList<>();

        for (int i=0; i < 5;i++){
            questions.add(Question.builder().question("questionTest"+ i).correctAnswer(Arrays.asList("answerTest" + i)).userAnswer("answerTest" + i).build());
        }
        return questions;
    }


}