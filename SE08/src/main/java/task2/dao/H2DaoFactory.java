package task2.dao;

import task2.cp.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Air on 17/02/2017.
 */
public class H2DaoFactory implements DaoFactory, AutoCloseable {

    ConnectionPool connectionPool = new ConnectionPool(3);

    @Override
    public Connection getConnection() throws SQLException {
            return connectionPool.getConnection();
    }

    @Override
    public AuthorDao getAuthorDao(Connection connection) {
        return new H2AuthorDao(connection);
    }

    @Override
    public BookDao getBookDao(Connection connection) {
        return new H2BookDao(connection);
    }

    @Override
    public void close() throws Exception {
        connectionPool.close();
    }
}
