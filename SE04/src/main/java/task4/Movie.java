package task4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 30/01/2017.
 */
public class Movie implements Serializable {

    private String title;
    private String year;
    private List<Actor> actors;

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
        actors = new ArrayList<>();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Actor[] getActors() {
        return (Actor[]) actors.toArray();
    }

    public void addActors(Actor[] actors) {
        for (Actor a: actors) {
            this.actors.add(a);
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(title + ", " + year + "\n");
        for (Actor actor: actors) {
            out.append(actor.toString()).append("\n");
        }
        return out.toString();
    }
}
