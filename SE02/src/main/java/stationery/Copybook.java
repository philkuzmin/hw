package stationery;

/**
 * Created by Air on 14/01/2017.
 */
public class Copybook extends Stationery {

    private int numberOfSheets;

    public Copybook(String title, double price, int numberOfSheets) {
        this.title = title;
        this.price = price;
        this.numberOfSheets = numberOfSheets;
    }

    public int getNumberOfSheets() {
        return numberOfSheets;
    }

    public void setNumberOfSheets(int numberOfSheets) {
        this.numberOfSheets = numberOfSheets;
    }
}
