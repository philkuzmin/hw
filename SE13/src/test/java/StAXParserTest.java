import org.junit.Assert;
import org.junit.Test;
import task2.StAXParser;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Air on 01/03/2017.
 */
public class StAXParserTest {

    @Test
    public void StAXParserTest() throws Exception {

        StAXParser parser = new StAXParser("src/main/resources/xml/breakfastmenu.xml");
        assertTrue(parser.parseFile().size() > 4);

    }
}
