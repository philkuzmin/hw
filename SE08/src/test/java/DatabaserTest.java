import org.junit.Test;
import task1.Databaser;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Air on 14/02/2017.
 */
public class DatabaserTest {

    @Test
    public void databaseTest() throws Exception {

        try (Databaser db = new Databaser()) {
            db.initDatabase();
            System.out.println("----- Initial database:");
            db.selectAll();

            System.out.println("----- Insert:");
            assertThat(db.insertQuery("John", "Smith", 22), is(1));
            db.selectAll();

            System.out.println("----- Update:");
            assertThat(db.updateQuery(4, "John", "MacCartney", 77), is(1));
            db.selectAll();

            System.out.println("----- Update several entries:");
            db.insertQuery("John", "Trump", 55);
            assertThat(db.renameUserQuery("John", "Lennon"), is(3));
            db.selectAll();

            System.out.println("----- Select single entry:");
            db.selectById(3);

            System.out.println("----- Drop table...");
            db.dropTable();
            db.selectAll();
        }

    }
}
