package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.notes.note.Note;

/**
 * Wraps all data at the Notebook level
 * Duplicates are not allowed (by .isSameNote comparison)
 */
public class Notebook implements ReadOnlyNotebook {

    private final NotesList notes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        notes = new NotesList();
    }

    public Notebook() {}

    /**
     * Creates a Reeve using the Notes in the {@code toBeCopied}
     */
    public Notebook(ReadOnlyNotebook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the notes list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNotes(List<Note> notes) {
        this.notes.setNotes(notes);
    }

    /**
     * Resets the existing data of this {@code Notebook} with {@code newData}.
     */
    public void resetData(ReadOnlyNotebook newData) {
        requireNonNull(newData);

        setNotes(newData.getNotesList());
    }

    //// Note-level operations

    /**
     * Returns true if a note has the same title as a {@code note} that already exists in the notebook.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notes.contains(note);
    }

    /**
     * Adds a note to the notebook.
     * The note must not have the same title as another note in the notebook.
     */
    public void addNote(Note p) {
        notes.add(p);
    }

    /**
     * Replaces the given note {@code target} in the list with {@code editedNote}.
     * {@code target} must exist in Reeve.
     * The title of {@code editedNote} must not be the same as another existing note
     * in the notebook.
     */
    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);

        notes.setNote(target, editedNote);
    }

    /**
     * Removes {@code key} from this {@code Reeve}.
     * {@code key} must exist in Reeve.
     */
    public void removeNote(Note key) {
        notes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return notes.asUnmodifiableObservableList().size() + " notes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Note> getNotesList() {
        return notes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notebook // instanceof handles nulls
                && notes.equals(((Notebook) other).notes));
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
