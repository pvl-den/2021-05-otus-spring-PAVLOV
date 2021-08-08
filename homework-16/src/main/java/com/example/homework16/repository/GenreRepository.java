package com.example.homework16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework11.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
