package ru.otus.homework13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework13.entity.Note;

public interface NotesRepository extends MongoRepository<Note, String> {

    void deleteByBookId(String bookId);
}
