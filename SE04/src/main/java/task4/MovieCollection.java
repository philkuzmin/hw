package task4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Air on 30/01/2017.
 */
public class MovieCollection implements Serializable {

    private List<Movie> movies;
    private File file;

    public MovieCollection() {
        file = new File("movies.bin");
    }

    public static void main(String[] args) {
        MovieCollection mc = new MovieCollection();
        mc.startApp();
    }

    public void startApp() {
        try {
            if (!loadFile(file)) {
                movies = new ArrayList<>();
            } else {
                System.out.println("Collection loaded!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Add movie\n2. Print collection\n3. Save & exit");
                switch (scanner.nextLine()) {
                    case "1":
                        System.out.println("Input title:");
                        String title = scanner.nextLine();
                        System.out.println("Input year:");
                        String year = scanner.nextLine();
                        System.out.println("How many actors you want to add?");
                        String num = scanner.nextLine();
                        try {
                            int i = Integer.parseInt(num);
                            Actor[] actors = new Actor[i];
                            for (int j = 0; j < i; j++) {
                                System.out.println("Actor " + (j + 1) + ": ");
                                System.out.println("First name: ");
                                String firstName = scanner.nextLine();
                                System.out.println("Last name: ");
                                String lastName = scanner.nextLine();
                                actors[j] = new Actor(firstName, lastName);
                            }
                            addMovie(title, year, actors);
                            System.out.println("Done!");
                        } catch (NumberFormatException ex) {
                            System.out.println("Wrong number!");
                            continue;
                        }
                        break;
                    case "2":
                        printCollection();
                        break;
                    case "3":
                        saveFile(file);
                        return;
                    default:
                        continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMovie(String title, String year, Actor... actors) {
        Movie m = new Movie(title, year);
        m.addActors(actors);
        movies.add(m);
    }

    private void printCollection() {
        System.out.println("Movie collection:\n");
        for (Movie m : movies) {
            System.out.println(m.toString() + "\n");
        }
        System.out.println("Total: " + movies.size() + " movies in collection.");
    }

    private boolean loadFile(File file) throws IOException {
        if (file != null) {
            if (file.exists()) {
                if (file.isFile() && file.canRead()) {
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                        Object obj = objectInputStream.readObject();
                        if (obj instanceof List) {
                            movies = (List<Movie>) obj;
                            return true;
                        } else {
                            return false;
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println("Something's went very wrong!");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void saveFile(File file) throws IOException {
        if (file != null) {
            if (file.exists()) {
                if (file.isFile() && file.canWrite()) {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                        objectOutputStream.writeObject(movies);
                    }
                } else {
                    throw new IOException("Can't write");
                }
            } else {
                file.createNewFile();
                saveFile(file);
            }
        }
    }
}
