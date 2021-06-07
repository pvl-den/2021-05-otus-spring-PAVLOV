package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.dao.QuestionsDao;
import ru.otus.dao.QuestionsDaoImpl;

@Configuration
public class QuestionsDaoConfig {

    @Bean
    public QuestionsDao questionsDao(@Value("${fileName}") String fileName) {
        return new QuestionsDaoImpl(fileName);
    }
}
