package stationery;

/**
 * Created by Air on 14/01/2017.
 */
public abstract class Stationery{

    protected String title;
    protected double price;

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

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stationery) {
            return ((Stationery) obj).getTitle().equals(title);
        }
        return false;
    }

    @Override
    public String toString() {
        return title + ", " + price;
    }
}
