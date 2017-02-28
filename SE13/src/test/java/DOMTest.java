import org.junit.Test;
import task2.DOMXMLParser;

import static org.junit.Assert.assertTrue;

/**
 * Created by Air on 01/03/2017.
 */
public class DOMTest {

    @Test
    public void DOMParserTest() throws Exception {

        DOMXMLParser parser = new DOMXMLParser("src/main/resources/xml/breakfastmenu.xml");
        assertTrue(parser.parseFile().size() == 5);

    }
}
