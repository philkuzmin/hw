import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import task2.PropertiesReader;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Air on 04/02/2017.
 */
public class PropertiesReaderTest {

    @Test
    public void getPropertyTest() throws Exception {

        PropertiesReader reader = new PropertiesReader(new File("test.properties"));
        assertThat(reader.getProperty("key"), is("value"));
        assertThat(reader.getProperty("test"), is("test"));

    }
}
