package ru.otus.homework23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework23.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}
