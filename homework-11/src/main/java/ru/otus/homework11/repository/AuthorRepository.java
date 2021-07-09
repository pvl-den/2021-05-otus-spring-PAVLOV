package ru.otus.homework11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework11.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select count(a) from Author a")
    long count();

    Author findByName(String name);

    Optional<Author> findById(long id);

    void deleteById(long id);
}
