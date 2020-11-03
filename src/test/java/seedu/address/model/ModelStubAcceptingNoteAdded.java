package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Note;

/**
 * A Model stub that always accept the note being added.
 */
public class ModelStubAcceptingNoteAdded extends ModelStub {
    public final ArrayList<Note> notesAdded = new ArrayList<>();

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notesAdded.stream().anyMatch(note::isSameNote);
    }

    @Override
    public void addNote(Note note) {
        notesAdded.add(note);
    }

    @Override
    public ReadOnlyNotebook getNotebook() {
        return new Notebook();
    }
}

