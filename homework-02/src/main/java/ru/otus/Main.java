package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.questions.QuestionsService;
import ru.otus.service.quiz.QuizService;
import ru.otus.service.quiz.QuizServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuizService quizService = context.getBean(QuizService.class);
        quizService.startQuiz();
    }
}
