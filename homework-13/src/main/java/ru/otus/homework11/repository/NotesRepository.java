package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework11.entity.Note;

public interface NotesRepository extends MongoRepository<Note, String> {

    void deleteByBookId(String bookId);
}
