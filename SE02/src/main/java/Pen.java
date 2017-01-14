/**
 * Created by Air on 14/01/2017.
 */
public class Pen {

    private String title;
    private double price;
    private String color;

    public Pen(String title, double price, String color) {
        this.title = title;
        this.price = price;
        this.color = color;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pen) {
            return ((Pen) obj).getTitle().equals(title);
        }
        return false;
    }

    @Override
    public String toString() {
        return title + ", " + color + ", " + price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
