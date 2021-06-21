package ru.otus.service.quiz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;
import ru.otus.service.core.IOService;
import ru.otus.service.core.MessagesService;
import ru.otus.service.questions.QuestionsService;
import ru.otus.service.user.UserService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {

    private final Double resultQuiz;
    private final Scanner sc;
    private final MessagesService messagesService;
    private final String[] availableLocales;
    private final IOService ioService;
    private final UserService userService;
    private final ResultService resultService;


    private final QuestionsService questionsService;

    public QuizServiceImpl(final QuestionsService questionsService,
                           final QuizConfig quizConfig,
                           final MessagesService messagesService,
                           final IOService ioService,
                           final UserService userService,
                           final ResultService resultService) {
        this.questionsService = questionsService;
        this.resultQuiz = quizConfig.getResultQuiz();
        this.availableLocales = quizConfig.getAvailableLocales();
        this.messagesService = messagesService;
        this.ioService = ioService;
        this.userService = userService;
        this.resultService = resultService;
        this.sc = new Scanner(System.in);
    }


    @Override
    public void startQuiz() {
        log.info("start testing");

        final Locale locale = messagesService.getLocale();

        final List<Question> allQuestions = questionsService.getAllQuestions(locale);

        if (allQuestions.isEmpty()) {
            log.error("the list of questions is empty");
            throw new IllegalStateException("the list of questions is empty");
        }

        questioning(allQuestions);

        final boolean isPassing = resultService.getResult(allQuestions, resultQuiz);

        resultService.outputResultQuiz(isPassing, userService.getUser());

        log.info("end testing");
    }

    @Override
    public void selectLanguage() {

        ioService.out(messagesService.getMessage("select_language"));
        messagesService.displayAvailableLanguages(availableLocales);

        String inputLocalStr = sc.nextLine();

        try {
            final int inputLocal = Integer.parseInt(inputLocalStr);
            messagesService.setLocale(inputLocal);
        } catch (Exception e) {
            log.error("incorrect locale input");
            throw new IllegalStateException("incorrect locale input. " + e.getMessage() + " " + e.getCause());
        }
    }

    private void questioning(final List<Question> allQuestions) {

        ioService.out(messagesService.getMessage("start_testing"));

        for (Question question : allQuestions) {
            ioService.out(question.getQuestion());
            question.setUserAnswer(sc.nextLine());
        }
    }
}

