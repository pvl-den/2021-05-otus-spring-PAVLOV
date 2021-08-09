package ru.otus.homework16;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework16Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Homework16Application.class, args);
        Console.main(args);
    }

}
