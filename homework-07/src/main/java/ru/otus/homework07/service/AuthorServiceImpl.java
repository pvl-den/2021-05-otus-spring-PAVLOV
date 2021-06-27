package ru.otus.homework07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework07.entity.Author;
import ru.otus.homework07.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public int count() {
        return authorRepository.count();
    }

    @Override
    public void insert(final Author author) {
        authorRepository.insert(author);
    }

    @Override
    public Author getById(final long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author getByName(String authorName) {
        return authorRepository.getByName(authorName);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public void deleteById(final long id) {
        authorRepository.deleteById(id);
    }
}
