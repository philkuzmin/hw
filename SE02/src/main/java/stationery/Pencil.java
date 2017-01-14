package stationery;

/**
 * Created by Air on 14/01/2017.
 */
public class Pencil extends Stationery{

    private String type;

    public Pencil(String title, double price, String type) {
        this.title = title;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
