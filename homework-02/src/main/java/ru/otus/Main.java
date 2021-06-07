package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.quiz.QuizService;

@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:application.properties")
@Configuration
public class Main {

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        context.getBean(QuizService.class).startQuiz();
    }
}
