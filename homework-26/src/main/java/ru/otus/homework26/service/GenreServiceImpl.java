package ru.otus.homework26.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework26.entity.mongo.Genre;
import ru.otus.homework26.entity.relationalstorage.RS_Genre;
import ru.otus.homework26.repository.relationalstorage.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    public RS_Genre convert(final Genre genre) {
        final RS_Genre rs_genre = RS_Genre.builder().name(genre.getName()).mongoId(genre.getId()).build();

        return genreRepository.save(rs_genre);
    }

    @Override
    public RS_Genre findByMongoId(final String id) {
        return genreRepository.findByMongoId(id);
    }
}
