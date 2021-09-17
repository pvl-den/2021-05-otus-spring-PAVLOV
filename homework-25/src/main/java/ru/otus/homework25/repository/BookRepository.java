package ru.otus.homework25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework25.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    Book findByName(String name);

}
