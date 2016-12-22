package task6;

import java.util.Scanner;

/**
 * Main application class
 * Created by Air on 13/12/2016.
 */
public class app {

    public static void main(String[] args) {

        startApp(new Notepad());

    }

    /**
     * Method starts the console interface of the notepad app
     * @param notepad notepad instance to work with
     */
    private static void startApp(Notepad notepad) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("\nAdd/Edit/Del/Show/Quit > ");
                String input = sc.nextLine();
                switch (input.trim().toLowerCase()) {
                    case "add":
                        System.out.print("Input note: ");
                        notepad.addNote(sc.nextLine());
                        notepad.showAllNotes();
                        break;

                    case "edit":
                        System.out.print("Input note number: ");
                        int indexEdit = Integer.parseInt(sc.nextLine());
                        System.out.print("Input new note: ");
                        notepad.editNote(indexEdit, sc.nextLine());
                        notepad.showAllNotes();
                        break;

                    case "del":
                        System.out.print("Input number to delete: ");
                        int indexDel = Integer.parseInt(sc.nextLine());
                        notepad.deleteNote(indexDel);
                        notepad.showAllNotes();
                        break;

                    case "show":
                        notepad.showAllNotes();
                        break;

                    case "quit":
                        sc.close();
                        return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }

}
