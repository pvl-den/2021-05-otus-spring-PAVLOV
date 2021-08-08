package ru.otus.homework16.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework16.entity.Genre;
import ru.otus.homework16.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Genre getById(final long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
