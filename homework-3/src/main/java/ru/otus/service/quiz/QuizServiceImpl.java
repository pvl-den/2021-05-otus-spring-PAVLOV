package ru.otus.service.quiz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.QuizConfig;
import ru.otus.domain.Question;
import ru.otus.domain.User;
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
    private final MessageSource messageSource;
    private Locale locale;
    private final String[] availableLocales;


    private final QuestionsService questionsService;

    public QuizServiceImpl(final QuestionsService questionsService,
                           final QuizConfig quizConfig,
                           final MessageSource messageSource) {
        this.questionsService = questionsService;
        this.resultQuiz = quizConfig.getResultQuiz();
        this.availableLocales = quizConfig.getAvailableLocales();
        this.messageSource = messageSource;
        this.sc = new Scanner(System.in);
        this.locale = Locale.getDefault();
    }

    @Override
    public void startQuiz() {
        log.info("start testing");

        System.out.println(messageSource.getMessage("select_language", new String[]{}, locale));

        for (int i = 0; i < availableLocales.length; i++) {
            String tag = availableLocales[i];
            System.out.println((i) + ". " + Locale.forLanguageTag(tag).getDisplayLanguage(Locale.forLanguageTag(tag)));
        }

        String inputLocal = sc.nextLine();

        try {
            locale = Locale.forLanguageTag(availableLocales[Integer.parseInt(inputLocal)]);
        } catch (Exception e) {
            log.error("incorrect locale input");
            throw new IllegalStateException("incorrect locale input. " + e.getMessage() + " " + e.getCause());
        }

        this.user = getUser();

        final List<Question> allQuestions = questionsService.getAllQuestions(locale);

        if (allQuestions.isEmpty()) {
            log.error("the list of questions is empty");
            throw new IllegalStateException("the list of questions is empty");
        }

        questioning(allQuestions);

        final boolean isPassing = getResult(allQuestions);

        outputResult(isPassing);

        log.info("end testing");
    }

    @Override
    public void outputResult(final boolean isPassing) {
        System.out.println(" " + messageSource.getMessage("end_testing", new String[]{}, locale));

        System.out.println(isPassing
                ? messageSource.getMessage("result_success", new String[]{}, locale)
                : messageSource.getMessage("result_fail", new String[]{}, locale));
    }

    private void questioning(final List<Question> allQuestions) {

        System.out.println(messageSource.getMessage("start_testing", new String[]{}, locale));

        for (Question question : allQuestions) {
            System.out.println(question.getQuestion());
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
        System.out.println(messageSource.getMessage("your_name", new String[]{}, locale));
        final String firstName = sc.nextLine();

        System.out.println(messageSource.getMessage("your_last_name", new String[]{}, locale));
        final String lastName = sc.nextLine();

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}

