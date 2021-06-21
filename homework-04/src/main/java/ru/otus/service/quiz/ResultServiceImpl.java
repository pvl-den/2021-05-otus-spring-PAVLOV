package ru.otus.service.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.core.IOService;
import ru.otus.service.core.MessagesService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final IOService ioService;
    private final MessagesService messagesService;

    @Override
    public void outputResultQuiz(final boolean isPassing, final User user) {
        ioService.out(isPassing
                ? messagesService.getMessage("result_success")
                : messagesService.getMessage("result_fail"));
    }

    @Override
    public boolean getResult(final List<Question> allQuestions,
                             final Double resultQuiz) {

        final AtomicInteger result = new AtomicInteger(0);

        allQuestions.forEach(q -> {
            if (q.getCorrectAnswer().toString().toUpperCase().contains(q.getUserAnswer().toUpperCase())) {
                result.incrementAndGet();
            }
        });
        return ((double) result.get() / allQuestions.size()) >= resultQuiz;
    }

}
