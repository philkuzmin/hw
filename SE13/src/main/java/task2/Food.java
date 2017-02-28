package task2;

/**
 * Created by Air on 28/02/2017.
 */
public class Food {

    private int id;
    private String name;
    private String price;
    private String description;
    private int calories;

    @Override
    public String toString() {
        return id + ". " + name + ", " + price + "\n" + description + ", " + calories + " calories.";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
