package ru.otus.service.quiz;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.questions.QuestionsService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Service
public class QuizServiceImpl implements QuizService {

    private final Double resultQuiz;
    private final Scanner sc;
    private User user;

    private final QuestionsService questionsService;

    public QuizServiceImpl(final QuestionsService questionsService,
                           @Value("${resultQuiz}") final Double resultQuiz) {
        this.questionsService = questionsService;
        this.resultQuiz = resultQuiz;
        this.sc = new Scanner(System.in);
        this.user = getUser();
    }


    @Override
    public void startQuiz() {

        final List<Question> allQuestions = questionsService.getAllQuestions();

        if (allQuestions.isEmpty()) {
            throw new IllegalStateException("the list of questions is empty");
        }

        questioning(allQuestions);

        final boolean isPassing = getResult(allQuestions);

        outputResult(isPassing);
    }

    private void outputResult(final boolean isPassing) {
        System.out.println("thank you for your answers " + user.getFirstName());
        System.out.println(isPassing ? "your result: SUCCESS" : "your result: FAIL");
    }

    private void questioning(final List<Question> allQuestions) {
        System.out.println("please answer the questions");
        for (Question question : allQuestions) {
            System.out.println(question.getQuestion());
            question.setUserAnswer(sc.nextLine());
        }
    }

    private boolean getResult(final List<Question> allQuestions) {
        final AtomicInteger result = new AtomicInteger(0);

        allQuestions.forEach(q -> {
            if (q.getCorrectAnswer().toString().toUpperCase().contains(q.getUserAnswer().toUpperCase())) {
                result.incrementAndGet();
            }
        });
        return ((double) result.get() / allQuestions.size()) >= resultQuiz;
    }

    private User getUser() {
        System.out.println("Enter your name: ");
        final String firstName = sc.nextLine();

        System.out.println("Enter your last name: ");
        final String lastName = sc.nextLine();

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}

