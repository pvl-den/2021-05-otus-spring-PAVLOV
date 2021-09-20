package ru.otus.homework26;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories(basePackages = "ru.otus.homework26.repository.mongo")
public class Homework26Application {

    public static void main(String[] args) throws SQLException {
                        Console.main(args);
        SpringApplication.run(Homework26Application.class, args);
    }

}
