package ru.otus.service.questions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.config.QuizConfig;
import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CreatingQuestionsServiceImpl implements CreatingQuestions {

    private final QuestionsDao questionsDao;
    private final QuizConfig quizConfig;


    @Override
    public List<Question> createQuestionsFromFile(final Locale locale) {

        final String fileNameByLocale = getFileNameByLocale(locale);
        final List<String[]> stringsFromfile;

        try {
            stringsFromfile = questionsDao.readFromFile(fileNameByLocale);
        } catch (Exception e) {
            log.error("file read error");
            return Collections.emptyList();
        }

        return buildQuestions(stringsFromfile);

    }

    private String getFileNameByLocale(final Locale locale) {
        return String.format("%s_%s.%s", quizConfig.getFileName(), locale.getLanguage(), quizConfig.getFileType());
    }

    private List<Question> buildQuestions(final List<String[]> stringList) {
        return stringList.stream().
                map(line -> Question.builder()
                        .question(line[0])
                        .correctAnswer(Arrays.asList(line).subList(1, line.length))
                        .build())
                .collect(Collectors.toList());
    }

}
