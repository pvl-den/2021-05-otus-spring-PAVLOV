package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.domain.Question;
import ru.otus.service.QuestionsService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        final QuestionsService service = context.getBean(QuestionsService.class);
        final List<Question> allQuestions1 = service.getAllQuestions();

        allQuestions1.forEach(System.out::println);
    }
}
