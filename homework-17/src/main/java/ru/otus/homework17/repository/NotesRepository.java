package ru.otus.homework17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework17.entity.Note;

public interface NotesRepository extends JpaRepository<Note, Long> {

    void deleteByBookId(long bookId);
}
