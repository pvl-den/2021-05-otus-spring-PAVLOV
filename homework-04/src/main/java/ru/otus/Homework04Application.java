package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.service.quiz.QuizServiceImpl;

@SpringBootApplication
public class Homework04Application {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Homework04Application.class, args);

        ctx.getBean(QuizServiceImpl.class)
                .startQuiz();
    }
}