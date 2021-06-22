package ru.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quiz")
@Data
public class QuizConfig {

    private String fileName;
    private String fileType;
    private Double resultQuiz;
    private String[] availableLocales;

}
