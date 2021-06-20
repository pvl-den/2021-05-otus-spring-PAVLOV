package ru.otus.service.core;

import java.util.Locale;

public interface MessagesService {

    void setLocale(int inputLocal);

    Locale getLocale();

    String getMessage(String messageName);

    void displayAvailableLanguages(String[] availableLocales);
}
