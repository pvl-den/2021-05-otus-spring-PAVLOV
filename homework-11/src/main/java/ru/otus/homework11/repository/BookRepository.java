package ru.otus.homework11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homework11.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select count(b) from Book b")
    long count();

    Book save(Book book);

    Book findById(long id);

    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    void deleteById(long id);

    Book findByName(String name);
}
