package accounting;

import stationery.Stationery;

import java.util.Comparator;

/**
 * Created by Air on 14/01/2017.
 */
public class CompareByPrice implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return (int)(o1.getPrice() - o2.getPrice());
    }
}
