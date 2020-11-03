package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.notes.note.Note;

/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithNote extends ModelStub {
    private final Note note;

    /**
     * {@code note} note to be included in the model
     */
    public ModelStubWithNote(Note note) {
        requireNonNull(note);
        this.note = note;
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return this.note.isSameNote(note);
    }
}
