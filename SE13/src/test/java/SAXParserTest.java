import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import task2.SAXParser;

/**
 * Created by Air on 28/02/2017.
 */
public class SAXParserTest {

    @Test
    public void SAXParserTest() throws Exception {

        XMLReader reader = XMLReaderFactory.createXMLReader();
        SAXParser handler = new SAXParser();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("src/main/resources/xml/breakfastmenu.xml"));

    }
}
