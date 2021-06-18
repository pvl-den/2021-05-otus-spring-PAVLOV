package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.config.QuizConfig;
import ru.otus.service.quiz.QuizServiceImpl;

@EnableConfigurationProperties(QuizConfig.class)
@SpringBootApplication
public class Homework3Application {


    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Homework3Application.class, args);

        ctx.getBean(QuizServiceImpl.class)
                .startQuiz();
    }
}