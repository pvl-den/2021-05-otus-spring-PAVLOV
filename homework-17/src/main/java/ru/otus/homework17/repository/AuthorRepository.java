package ru.otus.homework17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework17.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
