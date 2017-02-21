package task2.model;

/**
 * Created by Air on 15/02/2017.
 */
public class Book {

    private int id;
    private Author author;
    private String title;
    private String year;

    public Book() {
    }

    public Book(int id, Author author, String title, String year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return id + ". " + author.toString() + ", " + title + ", " + year;
    }
}
