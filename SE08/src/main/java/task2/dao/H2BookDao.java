package task2.dao;

import task2.model.Author;
import task2.model.Book;
import task2.model.Library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 17/02/2017.
 */
public class H2BookDao implements BookDao {

    private final Connection connection;

    public H2BookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book createBook(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Books (book_title, book_year, author_id) VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getYear());
            statement.setInt(3, book.getAuthor().getId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId((int) generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book readBook(int id) {
        Book book = new Book();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT book_id, book_title, book_year, author_id FROM Books WHERE id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                book.setId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("book_title"));
                book.setYear(resultSet.getString("book_year"));
                int authorId = resultSet.getInt("author_id");
                Author author = new Author();
                try (PreparedStatement authorStatement = connection.prepareStatement(
                        "SELECT first_name, last_name FROM Authors WHERE id = ?;")) {
                    authorStatement.setInt(1, authorId);
                    try (ResultSet authorResultSet = authorStatement.executeQuery()) {
                        authorResultSet.next();
                        author.setId(authorId);
                        author.setFirstName(authorResultSet.getString("first_name"));
                        author.setLastName(authorResultSet.getString("last_name"));
                    }
                }
                book.setAuthor(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Books SET book_title = ?, book_year = ?, author_id = ? WHERE book_id = ?;")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getYear());
            statement.setInt(3, book.getAuthor().getId());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteBook(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Books WHERE book_id = ?;")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT book_id, book_title, book_year, author_id FROM Books;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                try (PreparedStatement authorStatement = connection.prepareStatement(
                        "SELECT id, first_name, last_name FROM Authors ORDER BY id;");
                     ResultSet authorsResultSet = authorStatement.executeQuery()) {
                    ArrayList<Author> authors = new ArrayList<>();
                    while (authorsResultSet.next()) {
                        Author author = new Author();
                        author.setId(authorsResultSet.getInt("id"));
                        author.setFirstName(authorsResultSet.getString("first_name"));
                        author.setLastName(authorsResultSet.getString("last_name"));
                        authors.add(author);
                    }
                    while (resultSet.next()) {
                        Book book = new Book();
                        book.setId(resultSet.getInt("book_id"));
                        book.setTitle(resultSet.getString("book_title"));
                        book.setYear(resultSet.getString("book_year"));
                        book.setAuthor(authors.get(resultSet.getInt("author_id") - 1));
                        books.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}



