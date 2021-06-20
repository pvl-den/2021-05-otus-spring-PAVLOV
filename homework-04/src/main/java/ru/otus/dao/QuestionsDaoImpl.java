package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
@RequiredArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    @Override
    public List<String[]> readFromFile(String fileName) throws IOException {

        if (fileName.isBlank()) {
            throw new IllegalArgumentException("file name is empty");
        }

        final List<String[]> stringList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new ClassPathResource(fileName).getInputStream())) {

            extracted(stringList, scanner);

        } catch (IOException ex) {
            throw new IOException("file read error");
        }

        return stringList;
    }

    private void extracted(final List<String[]> stringList, final Scanner scanner) {
        final String DELIMITER = "\n";
        scanner.useDelimiter(DELIMITER);

        while (scanner.hasNext()) {
            stringList.add(scanner.next().split(";"));
        }
    }

}