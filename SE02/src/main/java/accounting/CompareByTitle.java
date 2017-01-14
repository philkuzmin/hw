package accounting;

import stationery.Stationery;

import java.util.Comparator;

/**
 * Created by Air on 14/01/2017.
 */
public class CompareByTitle implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
