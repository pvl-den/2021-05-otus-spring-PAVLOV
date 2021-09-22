package ru.otus.homework26.repository.relationalstorage;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework26.entity.relationalstorage.RS_Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<RS_Author, Long> {

    RS_Author findByMongoId(String mongoId);

    List<RS_Author> findAll();
}
