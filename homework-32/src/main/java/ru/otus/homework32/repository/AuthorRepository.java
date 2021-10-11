package ru.otus.homework32.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework32.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
