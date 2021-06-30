package ru.otus.homework09;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Homework09Application {

    public static void main(String[] args) throws SQLException {

        //        Console.main(args);
        SpringApplication.run(Homework09Application.class, args);
    }

}
