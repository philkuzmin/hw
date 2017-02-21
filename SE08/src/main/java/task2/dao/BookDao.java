package task2.dao;

import task2.model.Author;
import task2.model.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Air on 17/02/2017.
 */
public interface BookDao {

    Book createBook(Book book);

    Book readBook(int id);

    void updateBook(Book book) throws SQLException;

    void deleteBook(int id) throws SQLException;

    List<Book> getAllBooks();

}
