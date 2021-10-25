package ru.otus.homework32.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework32.entity.security.AuthedUser;

public interface AuthedUserRepository extends JpaRepository<AuthedUser, Long> {

    AuthedUser findByUsername(String username);

}
