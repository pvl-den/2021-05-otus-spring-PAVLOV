package ru.otus.homework17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework17.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

}
