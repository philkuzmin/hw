package task6;

/**
 * Simple notepad class
 * Created by Air on 13/12/2016.
 */
class Notepad {

    private Note[] notes;

    /**
     * Creates a new empty notepad with capacity of 10 notes.
     */
    Notepad() {
        notes = new Note[10];
    }

    /**
     * This method adds new note to the notepad.
     * @param text note text
     * @throws Exception will be thrown if the notepad is full
     */
    void addNote(String text) throws Exception {
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] == null) {
                notes[i] = new Note(text);
                return;
            }
        }
        throw new Exception("Sorry, this Notepad is full");
    }

    /**
     * Deletes the specified note in the notepad.
     * @param i index of the note to be deleted
     * @throws Exception will be thrown if there is no such index in this notepad
     */
    void deleteNote(int i) throws Exception {
        if ((i >= 0) && (i < notes.length)) {
            notes[i] = null;
        } else {
            throw new Exception("Wrong index!");
        }
    }

    /**
     * Method changes the text of the note.
     * @param i index of the note to change
     * @param text new text
     * @throws Exception will be thrown if the index is wrong
     */
    void editNote(int i, String text) throws Exception {
        if ((i >= 0) && (i < notes.length)) {
            notes[i].setNote(text);
        } else {
            throw new Exception("Wrong index!");
        }
    }

    /**
     * Prints all notes in the console
     */
    void showAllNotes() {
        System.out.println("All notes in this notepad:");
        for (int i = 0; i < notes.length; i++) {
            if(notes[i] != null) {
                System.out.println("Note " + i);
                System.out.println(notes[i].getNote());
                System.out.println();
            }
        }
    }
}
