package task2.dao;

import task2.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 17/02/2017.
 */
public class H2AuthorDao implements AuthorDao {

    private Connection connection;

    public H2AuthorDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author createAuthor(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Authors (first_name, last_name) VALUES (?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setId((int) generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author readAuthor(int id) throws SQLException {
        Author author = new Author();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, first_name, last_name FROM Authors WHERE id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                author.setId(resultSet.getInt("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
            }
        }
        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Authors SET first_name = ?, last_name = ? WHERE id = ?;")) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setInt(3, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Authors WHERE id = ?;")) {
            statement.setInt(1, author.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, first_name, last_name FROM Authors;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setId(resultSet.getInt("id"));
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
