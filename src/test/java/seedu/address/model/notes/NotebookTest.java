package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_GRADUATION;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.exceptions.DuplicateNoteException;
import seedu.address.testutil.notes.NoteBuilder;

public class NotebookTest {

    private final Notebook notebook = new Notebook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), notebook.getNotesList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notebook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNotebook_replacesData() {
        Notebook newData = getTypicalNotebook();
        notebook.resetData(newData);
        assertEquals(newData, notebook);
    }

    @Test
    public void resetData_withDuplicateNotes_throwsDuplicateNoteException() {
        // Two notes with the same title
        Note editedGraduation = new NoteBuilder(NOTE_GRADUATION).build();
        List<Note> newNotes = Arrays.asList(NOTE_GRADUATION, editedGraduation);
        NotebookStub newData = new NotebookStub(newNotes);

        assertThrows(DuplicateNoteException.class, () -> notebook.resetData(newData));
    }

    @Test
    public void hasNote_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notebook.hasNote(null));
    }

    @Test
    public void hasNote_noteNotInNotebook_returnsFalse() {
        assertFalse(notebook.hasNote(NOTE_GRADUATION));
    }

    @Test
    public void hasNote_noteInAddressBook_returnsTrue() {
        notebook.addNote(NOTE_GRADUATION);
        assertTrue(notebook.hasNote(NOTE_GRADUATION));
    }

    @Test
    public void hasNote_noteWithSameTitle_returnsTrue() {
        notebook.addNote(NOTE_GRADUATION);
        Note editedGraduation = new NoteBuilder(NOTE_GRADUATION).build();
        assertTrue(notebook.hasNote(editedGraduation));
    }

    @Test
    public void getNoteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                notebook.getNotesList().remove(0));
    }

    /**
     * A stub ReadOnlyNotebook whose note list can violate interface constraints.
     */
    private static class NotebookStub implements ReadOnlyNotebook {
        private final ObservableList<Note> notes = FXCollections.observableArrayList();

        NotebookStub(Collection<Note> notes) {
            this.notes.setAll(notes);
        }

        @Override
        public ObservableList<Note> getNotesList() {
            return notes;
        }
    }

}
