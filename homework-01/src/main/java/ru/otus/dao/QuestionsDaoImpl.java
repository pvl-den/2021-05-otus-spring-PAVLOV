package ru.otus.dao;

import lombok.Data;
import ru.otus.domain.Question;

import java.util.List;

import static ru.otus.service.util.Utilities.readQuestionsFromFile;

@Data
public class QuestionsDaoImpl implements QuestionsDao {

    private String fileName;

    @Override
    public List<Question> getAllQuestions() {
        return readQuestionsFromFile(fileName);
    }

}
