package ru.otus.homework32.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework32.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

}
