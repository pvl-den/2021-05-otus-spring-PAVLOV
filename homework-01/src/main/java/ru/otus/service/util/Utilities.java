package ru.otus.service.util;

import ru.otus.domain.Question;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class Utilities {

    public static List<Question> readQuestionsFromFile(final String fileName) {
        final List<String[]> stringList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get(fileName).toFile())) {
            final String DELIMITER = "\n";
            scanner.useDelimiter(DELIMITER);

            while (scanner.hasNext()) {
                stringList.add(scanner.next().split(";"));
            }

            questions = stringList.stream().map(line -> Question.builder()
                    .question(line[0])
                    .answer(Arrays.asList(line).subList(1, line.length))
                    .build())
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return questions;
    }
}

