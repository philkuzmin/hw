import accounting.CompareByPrice;
import accounting.CompareByPriceAndTitle;
import accounting.CompareByTitle;
import accounting.StationeryAccounting;
import org.junit.Assert;
import org.junit.Test;
import stationery.Copybook;
import stationery.Pen;
import stationery.Pencil;
import stationery.Stationery;

import java.util.Collections;
import java.util.Comparator;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Air on 14/01/2017.
 */
public class StationeryAccountingTest {

    private StationeryAccounting sa = new StationeryAccounting();
    private Pen pen1 = new Pen("SuperPen", 5.0, "black");
    private Pen pen2 = new Pen("SuperPen", 3.0, "black");
    private Pen pen3 = new Pen("AlfaPen", 3.0, "black");
    private Pencil pencil1 = new Pencil("MegaPencil", 9.0, "color");
    private Copybook copybook1 = new Copybook("Notepad", 12.0, 48);

    @Test
    public void sumCalculationTest() {
        sa.addItem(pen1);
        sa.addItem(pencil1);
        sa.addItem(copybook1);
        assertEquals(26.0, sa.calculatePrice());
    }

    @Test
    public void sortTest() {
        sa.addItem(pen1);
        sa.addItem(pencil1);
        sa.addItem(copybook1);
        sa.addItem(pen2);
        sa.addItem(pen3);
        Comparator<Stationery> cbp = new CompareByPrice();
        Comparator<Stationery> cbt = new CompareByTitle();
        Comparator<Stationery> cbpt = new CompareByPriceAndTitle();

        sa.printAll();
        Collections.sort(sa.getItems(), cbp);
        System.out.println("------------ by price");
        sa.printAll();
        System.out.println("------------ by title");
        Collections.sort(sa.getItems(), cbt);
        sa.printAll();
        System.out.println("------------ by price/title");
        Collections.sort(sa.getItems(), cbpt);
        sa.printAll();
    }
}
