package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс Question")
class QuestionTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("testQuestion", Collections.singletonList("testcorrectAnswer"), "userAnswer");

        assertThat(question.getQuestion())
                .isNotNull()
                .isEqualTo("testQuestion");
    }

}