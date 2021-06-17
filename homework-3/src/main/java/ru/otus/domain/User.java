package ru.otus.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class User {

    private final String firstName;

    private final String lastName;
}
