package ru.otus.homework26.repository.relationalstorage;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework26.entity.relationalstorage.RS_Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<RS_Genre, Long> {

    RS_Genre findByMongoId(String id);

    @Override
    List<RS_Genre> findAll();
}
