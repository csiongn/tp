package seedu.address.testutil.notes;

import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.note.Note;

/**
 * A utility class to help with building Notebook objects.
 * Example usage: <br>
 *     {@code Notebook notebook = new NotebookBuilder().withTitle("hehe").build();}
 */
public class NotebookBuilder {

    private Notebook notebook;

    public NotebookBuilder() {
        notebook = new Notebook();
    }

    public NotebookBuilder(Notebook notebook) {
        this.notebook = notebook;
    }

    /**
     * Adds a new {@code Note} to the {@code Notebook} that we are building.
     */
    public NotebookBuilder withNote(Note note) {
        notebook.addNote(note);
        return this;
    }

    public Notebook build() {
        return notebook;
    }
}
