import org.junit.Test;
import task2.SymbolStreamsOnly;

/**
 * Created by iMac on 29/01/17.
 */
public class SymbolStreamsOnlyTest {

    @Test
    public void byteStreamTest() throws Exception {

        SymbolStreamsOnly ss = new SymbolStreamsOnly("../SE03/src/main/java/task3/TextAnalyzer.java");
        ss.analyze();

    }

}
