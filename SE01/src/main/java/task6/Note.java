package task6;

/**
 * Single note
 * Created by Air on 13/12/2016.
 */
class Note {

    private String note;

    /**
     * Creates new note with the specified text
     * @param note text of the note
     */
    public Note(String note) {
        this.note = note;
    }

    /**
     * Changes the text of the current note
     * @param note new text
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Returns the current note instance
     * @return Note instance
     */
    public String getNote() {
        return note;
    }
}
