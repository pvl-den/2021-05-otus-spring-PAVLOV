package ru.otus.service.quiz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.IOService;
import ru.otus.service.MessagesService;
import ru.otus.service.questions.QuestionsService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {

    private final Double resultQuiz;
    private final Scanner sc;
    private User user;
    private Locale locale;
    private final MessagesService messagesService;
    private final String[] availableLocales;
    private final IOService ioService;


    private final QuestionsService questionsService;

    public QuizServiceImpl(final QuestionsService questionsService,
                           final QuizConfig quizConfig,
                           final MessagesService messagesService,
                           final IOService ioService) {
        this.questionsService = questionsService;
        this.resultQuiz = quizConfig.getResultQuiz();
        this.availableLocales = quizConfig.getAvailableLocales();
        this.messagesService = messagesService;
        this.ioService = ioService;
        this.sc = new Scanner(System.in);
        this.locale = Locale.getDefault();
    }

    @Override
    public void startQuiz() {
        log.info("start testing");

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
        this.locale = messagesService.getLocale();
        this.user = getUser();

        final List<Question> allQuestions = questionsService.getAllQuestions(locale);

        if (allQuestions.isEmpty()) {
            log.error("the list of questions is empty");
            throw new IllegalStateException("the list of questions is empty");
        }

        questioning(allQuestions);

        final boolean isPassing = getResult(allQuestions);

        outputResultQuiz(isPassing);

        log.info("end testing");
    }

    @Override
    public void outputResultQuiz(final boolean isPassing) {
        ioService.out(" " + messagesService.getMessage("end_testing"));

        ioService.out(isPassing
                ? messagesService.getMessage("result_success")
                : messagesService.getMessage("result_fail"));
    }

    private void questioning(final List<Question> allQuestions) {

        ioService.out(messagesService.getMessage("start_testing"));

        for (Question question : allQuestions) {
            ioService.out(question.getQuestion());
            question.setUserAnswer(sc.nextLine());
        }
    }

    @Override
    public boolean getResult(final List<Question> allQuestions) {
        final AtomicInteger result = new AtomicInteger(0);

        allQuestions.forEach(q -> {
            if (q.getCorrectAnswer().toString().toUpperCase().contains(q.getUserAnswer().toUpperCase())) {
                result.incrementAndGet();
            }
        });
        return ((double) result.get() / allQuestions.size()) >= resultQuiz;
    }

    private User getUser() {
        ioService.out(messagesService.getMessage("your_name"));
        final String firstName = sc.nextLine();

        ioService.out(messagesService.getMessage("your_last_name"));
        final String lastName = sc.nextLine();

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}

