package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс Question")
class DomainTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructorQuestion() {
        Question question = new Question("testQuestion", Collections.singletonList("testcorrectAnswer"), "userAnswer");

        assertThat(question.getQuestion())
                .isNotNull()
                .isEqualTo("testQuestion");
    }

    @Test
    void shouldHaveCorrectConstructorUser() {
        User user = new User("testFirstName", "testLastName");
        assertThat(user.getFirstName())
                .isNotNull()
                .isEqualTo("testFirstName");
    }

}