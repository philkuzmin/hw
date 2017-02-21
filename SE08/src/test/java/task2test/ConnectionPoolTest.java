package task2test;

import org.junit.Test;
import task2.cp.ConnectionPool;

import java.sql.Connection;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Air on 17/02/2017.
 */
public class ConnectionPoolTest {

    @Test
    public void connectionPoolTest() throws Exception {

        try (ConnectionPool connectionPool = new ConnectionPool(5)) {
            Connection connection = connectionPool.getConnection();
            Connection connection2 = connectionPool.getConnection();
            assertTrue(connection != null);
            assertTrue(!connection.isClosed());
        }

    }
}
