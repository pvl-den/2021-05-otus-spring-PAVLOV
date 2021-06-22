package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.domain.User;
import ru.otus.service.core.MessagesService;
import ru.otus.service.quiz.QuizService;
import ru.otus.service.user.UserService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final QuizService quizService;
    private final MessagesService messagesService;
    private final UserService userService;
    public User user;


    @ShellMethod(value = "Выбор языка", key = {"lg", "selectLanguage"})
    public String selectLanguage() {
        quizService.selectLanguage();
        String[] params = new String[]{messagesService.getLocale().getDisplayLanguage(messagesService.getLocale())};

        return "выбран " + params[0];
    }

    @ShellMethod(value = "Регистрация пользователя", key = {"l", "login"})
    public String login() {
        userService.loginUser();
        user = userService.getUser();
        return messagesService.getMessage("welcome") + " " + user.getFirstName();
    }

    @ShellMethod(value = "Запускает тестирование", key = {"s", "start"})
    @ShellMethodAvailability(value = "isLoginUser")
    public String start() {
        quizService.startQuiz();
        return messagesService.getMessage("end_testing") + " " + user.getFirstName();
    }

    private Availability isLoginUser() {
        return user == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
