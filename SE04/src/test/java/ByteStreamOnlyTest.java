import org.junit.Test;
import task1.ByteStreamOnly;

/**
 * Created by iMac on 29/01/17.
 */
public class ByteStreamOnlyTest {

    @Test
    public void byteStreamTest() throws Exception {

        ByteStreamOnly bso = new ByteStreamOnly("../SE03/src/main/java/task3/TextAnalyzer.java");
        bso.analyze();

    }
}
