package ru.otus.homework07.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework07.entity.Author;
import ru.otus.homework07.entity.Book;
import ru.otus.homework07.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbc implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject(
                "select count(b.id) from books b"
                , Integer.class);
    }

    @Override
    public void insert(final Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", book.getName())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());

        namedParameterJdbcOperations.update(
                "insert into books (`name`, author_id, genre_id) values (:name, :author_id, :genre_id)",
                params, keyHolder
        );
    }

    @Override
    public Book getById(final long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select\n" +
                        "   b.id,\n" +
                        "   b.name, \n" +
                        "   b.author_id, \n" +
                        "   b.genre_id,\n" +
                        "   a.name as name_author,\n" +
                        "   g.name as name_genre\n" +
                        "from books b\n" +
                        "  join authors a on b.author_id = a.id \n" +
                        "  join genres g on b.genre_id = g.id \n" +
                        "where b.id = :id",
                Map.of("id", id),
                new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select\n" +
                        "   b.id,\n" +
                        "   b.name, \n" +
                        "   b.author_id, \n" +
                        "   b.genre_id,\n" +
                        "   a.name as name_author,\n" +
                        "   g.name as name_genre\n" +
                        "from books b\n" +
                        "  join authors a on b.author_id = a.id \n" +
                        "  join genres g on b.genre_id = g.id", new BookMapper()
        );
    }

    @Override
    public void deleteById(final long id) {
        namedParameterJdbcOperations.update(
                "delete from books where id = :id",
                Map.of("id", id)
        );
    }

    @Override
    public Book getByName(final String name) {
        return namedParameterJdbcOperations.queryForObject(
                "select\n" +
                        "   b.id,\n" +
                        "   b.name, \n" +
                        "   b.author_id, \n" +
                        "   b.genre_id,\n" +
                        "   a.name as name_author,\n" +
                        "   g.name as name_genre\n" +
                        "from books b\n" +
                        "  join authors a on b.author_id = a.id \n" +
                        "  join genres g on b.genre_id = g.id \n" +
                        "where b.name = :name",
                Map.of("name", name),
                new BookMapper()
        );

    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            final long bookId = resultSet.getLong("id");
            final String bookName = resultSet.getString("name");

            final long authorId = resultSet.getLong("author_id");
            final String authorName = resultSet.getString("name_author");
            final Author author = new Author(authorId, authorName);

            final long genreId = resultSet.getLong("genre_id");
            final String genreName = resultSet.getString("name_genre");
            final Genre genre = new Genre(genreId, genreName);

            return Book.builder()
                    .id(bookId)
                    .name(bookName)
                    .author(author)
                    .genre(genre)
                    .build();
        }
    }
}
