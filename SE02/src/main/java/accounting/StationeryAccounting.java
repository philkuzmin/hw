package accounting;

import java.util.ArrayList;
import stationery.Stationery;

/**
 * Created by Air on 14/01/2017.
 */
public class StationeryAccounting {

    private ArrayList<Stationery> items = new ArrayList<>();

    public void addItem(Stationery item) {
        items.add(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public double calculatePrice() {
        double price = 0;
        for (Stationery item: items) {
            price += item.getPrice();
        }
        return price;
    }

    public ArrayList<Stationery> getItems() {
        return items;
    }

    public void printAll() {
        for (Stationery s: items) {
            System.out.println(s.toString());
        }
    }
}
