package ru.otus.homework25.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework25.entity.security.AuthedUser;

public interface AuthedUserRepository extends JpaRepository<AuthedUser, Long> {

    AuthedUser findByUsername(String username);

}
