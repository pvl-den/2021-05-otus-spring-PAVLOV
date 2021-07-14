package ru.otus.homework11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework11.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
