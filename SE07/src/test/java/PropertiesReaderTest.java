import org.junit.Test;
import task2.PropertiesReader;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Air on 12/02/2017.
 */
public class PropertiesReaderTest {

    @Test
    public void getPropertyTest() throws Exception {

        PropertiesReader reader = new PropertiesReader(new File("src/test/resources/test.properties"));
        assertThat(reader.getProperty("key"), is("value"));
        assertThat(reader.getProperty("test"), is("test"));

    }

}