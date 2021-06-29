package ru.otus.homework07.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework07.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJdbc implements AuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(a.id) from authors a", Integer.class);
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations.update("insert into authors (`name`) values (:name)",
                Map.of("name", author.getName()));
    }

    @Override
    public Author getById(final long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select authors.id, authors.name from authors where id = :id",
                Map.of("id", id),
                new AuthorMapper()
        );
    }

    @Override
    public Author getByName(String name) {
        return namedParameterJdbcOperations.queryForObject(
                "select a.id, a.name from authors a where name = :name",
                Map.of("name", name),
                new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query(
                "select a.id, a.name from authors a", new AuthorMapper()
        );
    }

    @Override
    public void deleteById(final long id) {

        namedParameterJdbcOperations.update(
                "delete from authors where id = :id",
                Map.of("id", id)
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            final long id = resultSet.getLong("id");
            final String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
