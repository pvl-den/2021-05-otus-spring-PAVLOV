package ru.otus.homework17.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework17.entity.Genre;
import ru.otus.homework17.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre getById(final long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByName(String authorName) {
        return genreRepository.findByName(authorName);
    }

}
