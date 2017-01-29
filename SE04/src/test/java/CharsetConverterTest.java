import org.junit.Test;
import task3.CharsetConverter;

/**
 * Created by iMac on 30/01/17.
 */
public class CharsetConverterTest {

    @Test
    public void charsetConverterTest() throws Exception {

        CharsetConverter charsetConverter = new CharsetConverter("utf8.txt");
        charsetConverter.convert();

    }
}
