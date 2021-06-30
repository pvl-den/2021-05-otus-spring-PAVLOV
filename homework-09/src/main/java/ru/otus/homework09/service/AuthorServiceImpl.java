package ru.otus.homework09.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework09.entity.Author;
import ru.otus.homework09.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public int count() {
        return authorRepository.count();
    }

    @Override
    @Transactional
    public Author save(final Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(final long id) {
        return authorRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByName(String authorName) {
        return authorRepository.getByName(authorName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    @Transactional
    public int deleteById(final long id) {
        return authorRepository.deleteById(id);
    }
}
