package task2.dao;

import task2.model.Author;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Air on 17/02/2017.
 */
public interface AuthorDao {

    Author createAuthor(Author author);

    Author readAuthor(int id) throws SQLException;

    void updateAuthor(Author author);

    void deleteAuthor(Author author);

    List<Author> getAllAuthors();

}
