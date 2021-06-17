package ru.otus.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Data
@Repository
@Slf4j
public class ReadFromFileImpl implements ReadFromFile {

    @Override
    public List<Question> readQuestionsFromFile(String fileName) {

        if (fileName.isBlank()) {
            log.error("incorrect file name");
            throw new IllegalArgumentException("incorrect file name");
        }

        final List<String[]> stringList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(getInputStream(fileName))) {

            parseFile(stringList, scanner);
            questions = getQuestions(stringList);

        } catch (IOException ex) {
            log.error("file read error");
            ex.printStackTrace();
        }
        return questions;
    }

    @Override
    public InputStream getInputStream(String fileName) throws IOException {
        try {
            return new ClassPathResource(fileName).getInputStream();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void parseFile(final List<String[]> stringList, final Scanner scanner) {
        final String DELIMITER = "\n";
        scanner.useDelimiter(DELIMITER);

        while (scanner.hasNext()) {
            stringList.add(scanner.next().split(";"));
        }
    }

    @Override
    public List<Question> getQuestions(final List<String[]> stringList) {
        return stringList.stream().
                map(line -> Question.builder()
                        .question(line[0])
                        .correctAnswer(Arrays.asList(line).subList(1, line.length))
                        .build())
                .collect(Collectors.toList());
    }
}
