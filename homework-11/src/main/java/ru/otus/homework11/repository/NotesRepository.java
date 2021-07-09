package ru.otus.homework11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework11.entity.Note;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Long> {

    Note findById(long id);

    List<Note> findAll();

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
