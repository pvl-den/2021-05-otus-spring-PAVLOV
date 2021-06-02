package ru.otus.dao;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import ru.otus.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Data
public class QuestionsDaoImpl implements QuestionsDao {

    private final String fileName;

    public QuestionsDaoImpl(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getAllQuestions() {
        return readQuestionsFromFile(fileName);
    }

    private List<Question> readQuestionsFromFile(final String fileName) {
        final List<String[]> stringList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(new ClassPathResource(fileName).getInputStream())) {

            final String DELIMITER = "\n";
            scanner.useDelimiter(DELIMITER);

            while (scanner.hasNext()) {
                stringList.add(scanner.next().split(";"));
            }

            questions = getQuestions(stringList);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return questions;
    }

    private List<Question> getQuestions(final List<String[]> stringList) {
        return stringList.stream().
                map(line -> Question.builder()
                        .question(line[0])
                        .correctAnswer(Arrays.asList(line).subList(1, line.length))
                        .build())
                .collect(Collectors.toList());
    }
}
