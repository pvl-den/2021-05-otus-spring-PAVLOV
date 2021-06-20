package ru.otus.service.user;

import org.springframework.stereotype.Service;
import ru.otus.domain.User;
import ru.otus.service.core.IOService;
import ru.otus.service.core.MessagesService;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {

    private final MessagesService messagesService;
    private final IOService ioService;
    private final Scanner sc;
    private User user;

    public UserServiceImpl(final MessagesService messagesService,
                           final IOService ioService) {
        this.messagesService = messagesService;
        this.ioService = ioService;
        this.sc = new Scanner(System.in);
        this.user = new User();
    }

    @Override
    public void loginUser() {
        ioService.out(messagesService.getMessage("your_name"));
        final String firstName = sc.nextLine();
        user.setFirstName(firstName);

        ioService.out(messagesService.getMessage("your_last_name"));
        final String lastName = sc.nextLine();
        user.setLastName(lastName);
    }

    @Override
    public User getUser() {
        return user;
    }
}
