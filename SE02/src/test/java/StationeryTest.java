import org.junit.Assert;
import org.junit.Test;
import stationery.Pen;

/**
 * Created by Air on 17/01/2017.
 */
public class StationeryTest {

    private Pen pen1 = new Pen("SuperPen", 5.0, "black");
    private Pen pen2 = new Pen("SuperPen", 3.0, "black");
    private Pen pen3 = new Pen("AlfaPen", 3.0, "black");

    @Test
    public void testEquals() {
        Assert.assertTrue(pen1.equals(pen2));
        Assert.assertFalse(pen1.equals(pen3));
    }

}
