package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_BUY_COFFEE;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_GRADUATION;

import org.junit.jupiter.api.Test;

import seedu.address.model.notes.note.Note;
import seedu.address.testutil.notes.NoteBuilder;

public class NoteTest {

    @Test
    public void isSameNote() {
        // same object -> returns true
        assertTrue(NOTE_BUY_COFFEE.isSameNote(NOTE_BUY_COFFEE));

        // null -> returns false
        assertFalse(NOTE_BUY_COFFEE.isSameNote(null));

        // different title -> returns false
        Note editedCoffeeNote = new NoteBuilder(NOTE_BUY_COFFEE).withTitle("hehe").build();
        assertFalse(NOTE_BUY_COFFEE.isSameNote(editedCoffeeNote));

        // different description same title -> returns true
        editedCoffeeNote = new NoteBuilder(NOTE_BUY_COFFEE).withDescription("haha").build();
        assertTrue(NOTE_BUY_COFFEE.isSameNote(editedCoffeeNote));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Note graduationNoteCopy = new NoteBuilder(NOTE_GRADUATION).build();
        assertTrue(NOTE_GRADUATION.equals(graduationNoteCopy));

        // same object -> returns true
        assertTrue(NOTE_GRADUATION.equals(NOTE_GRADUATION));

        // null -> returns false
        assertFalse(NOTE_GRADUATION.equals(null));

        // different type -> returns false
        assertFalse(NOTE_GRADUATION.equals(5));

        // different note -> returns false
        assertFalse(NOTE_GRADUATION.equals(NOTE_BUY_COFFEE));

        // different title -> returns false
        Note editedCoffeeNote = new NoteBuilder(NOTE_BUY_COFFEE).withTitle("hehe").build();
        assertFalse(NOTE_BUY_COFFEE.equals(editedCoffeeNote));

        // different description -> returns false
        editedCoffeeNote = new NoteBuilder(NOTE_BUY_COFFEE).withDescription("haha").build();
        assertFalse(NOTE_BUY_COFFEE.equals(editedCoffeeNote));

    }

}
