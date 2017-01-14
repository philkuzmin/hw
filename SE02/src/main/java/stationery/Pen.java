package stationery;

/**
 * Created by Air on 14/01/2017.
 */
public class Pen extends Stationery{

    private String color;

    public Pen(String title, double price, String color) {
        this.title = title;
        this.price = price;
        this.color = color;
    }

    @Override
    public String toString() {
        return title + ", " + color + ", " + price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
