package task2.dao;

import task2.model.Author;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Air on 17/02/2017.
 */
public interface DaoFactory extends AutoCloseable{

    Connection getConnection() throws SQLException;

    AuthorDao getAuthorDao(Connection connection);

    BookDao getBookDao(Connection connection);

}
