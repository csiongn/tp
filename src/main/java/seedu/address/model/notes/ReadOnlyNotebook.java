package seedu.address.model.notes;

import javafx.collections.ObservableList;
import seedu.address.model.notes.note.Note;

/**
 * Unmodifiable view of a notebook
 */
public interface ReadOnlyNotebook {

    /**
     * Returns an unmodifiable view of the list of notes.
     * This list will not contain any duplicate notes.
     */
    ObservableList<Note> getNotesList();

}
