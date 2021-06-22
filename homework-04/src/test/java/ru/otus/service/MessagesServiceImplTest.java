package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.service.core.MessagesService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class MessagesServiceImplTest {

    @Autowired
    private MessagesService messagesService;

    @Test
    void getLocaleEn() {
        messagesService.setLocale(1);

        Locale locale = messagesService.getLocale();
        assertThat(locale).isNotNull()
                .isEqualTo(Locale.forLanguageTag("en"));
    }

    @Test
    void getLocaleRu() {
        messagesService.setLocale(0);

        Locale locale = messagesService.getLocale();
        assertThat(locale).isNotNull()
                .isEqualTo(Locale.forLanguageTag("ru"));
    }

    @Test
    void getLocaleDefault() {
        Locale locale = messagesService.getLocale();
        assertThat(locale.getDisplayLanguage()).isNotNull()
                .isEqualTo(Locale.getDefault().getDisplayLanguage());
    }

}