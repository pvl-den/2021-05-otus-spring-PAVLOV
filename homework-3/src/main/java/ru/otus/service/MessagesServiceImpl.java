package ru.otus.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.QuizConfig;

import java.util.Locale;

@Service
public class MessagesServiceImpl implements MessagesService {

    private final MessageSource messageSource;
    private final String[] availableLanguages;
    private final QuizConfig quizConfig;
    private Locale locale;
    private final IOService ioService;

    public MessagesServiceImpl(final MessageSource messageSource,
                               final QuizConfig quizConfig,
                               final IOService ioService) {
        this.messageSource = messageSource;
        this.availableLanguages = quizConfig.getAvailableLocales();
        this.quizConfig = quizConfig;
        this.ioService = ioService;
        this.locale = Locale.getDefault();
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public String getMessage(final String messageName) {
        return messageSource.getMessage(messageName, new String[]{}, locale);
    }

    @Override
    public void displayAvailableLanguages(final String[] availableLocales) {
        for (int i = 0; i < availableLocales.length; i++) {
            final String tag = availableLocales[i];
            ioService.out((i) + ". " + Locale.forLanguageTag(tag).getDisplayLanguage(Locale.forLanguageTag(tag)));
        }
    }

    @Override
    public void setLocale(int inputLocal) {
        this.locale = Locale.forLanguageTag(availableLanguages[inputLocal]);
    }
}
