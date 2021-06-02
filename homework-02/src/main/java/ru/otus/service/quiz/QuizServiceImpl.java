package ru.otus.service.quiz;

import lombok.Data;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.questions.QuestionsService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class QuizServiceImpl implements QuizService {
    private final Scanner sc;
    private User user;

    private final QuestionsService questionsService;

    public QuizServiceImpl(final QuestionsService questionsService) {
        this.questionsService = questionsService;
        this.sc = new Scanner(System.in);
        this.user = getUser();
    }


    @Override
    public void startQuiz() {

        final List<Question> allQuestions = questionsService.getAllQuestions();

        if (allQuestions.isEmpty()) {
            throw new IllegalStateException("the list of questions is empty");
        }

        System.out.println("please answer the questions");
        for (Question question : allQuestions) {
            System.out.println(question.getQuestion());
            question.setUserAnswer(sc.nextLine());
        }

        final int result = getResult(allQuestions);

        System.out.println("thank you for your answers " + user.getFirstName() + " " + user.getLastName());
        System.out.println("your score: " + result + " points");
    }

    private int getResult(final List<Question> allQuestions) {
        final AtomicInteger result = new AtomicInteger(0);

        allQuestions.forEach(q -> {
            if (q.getCorrectAnswer().contains(q.getUserAnswer())) result.incrementAndGet();
        });
        return result.get();
    }

    public User getUser() {
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

