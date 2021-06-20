package ru.otus.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;

import java.util.List;
import java.util.Locale;

@Data
@Repository
@RequiredArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final QuizConfig quizConfig;
    private final ReadFromFileDao readFromFile;

    @Override
    public List<Question> getAllQuestions(final Locale locale) {
        return readFromFile.readQuestionsFromFile(getFileNameByLocale(locale));
    }

    private String getFileNameByLocale(final Locale locale) {
        return String.format("%s_%s.%s", quizConfig.getFileName(), locale.getLanguage(), quizConfig.getFileType());
    }

}