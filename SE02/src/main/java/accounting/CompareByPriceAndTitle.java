package accounting;

import stationery.Stationery;

import java.util.Comparator;

/**
 * Created by Air on 14/01/2017.
 */
public class CompareByPriceAndTitle implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {

        int res = (int) (o1.getPrice() - o2.getPrice());
        if (res != 0) {
            return res;
        }
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
