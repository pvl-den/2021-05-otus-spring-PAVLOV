package ru.otus.homework23.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework23.entity.Author;
import ru.otus.homework23.entity.security.AuthedUser;

public interface AuthedUserRepository extends JpaRepository<AuthedUser, Long> {

    AuthedUser findByUsername(String username);

}
