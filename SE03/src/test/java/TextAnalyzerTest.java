import org.junit.Before;
import org.junit.Test;
import task3.TextAnalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Air on 25/01/2017.
 */
public class TextAnalyzerTest {

    TextAnalyzer ta = new TextAnalyzer("Java.SE.03.Information handling_task_attachment.html");
   // TextAnalyzer ta = new TextAnalyzer("test.txt");

    @Test
    public void testTextAnalyser() throws Exception {

        System.out.println(ta.findStrings());
        ta.printStrings();

    }

    @Test
    public void testRefAnalyze() throws Exception {

        assertFalse(ta.analyzeRefs());

    }
}
