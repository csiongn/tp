package seedu.address.testutil.notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.note.Note;

/**
 * A utility class containing a list of {@code Note} objects to be used in tests.
 */
public class TypicalNotes {

    public static final Note NOTE_EXISTENTIAL_CRISIS = new NoteBuilder().withTitle("W10 blues")
            .withDescription("why must i do cs2103").build();
    public static final Note NOTE_GRADUATION = new NoteBuilder().withTitle("Graduation :(")
            .withDescription("write cards for people").build();
    public static final Note NOTE_BUY_COFFEE = new NoteBuilder().withTitle("JY!!!")
            .withDescription("Cafeine is good...").build();

    // manually added
    public static final Note NOTE_TODO = new NoteBuilder().withTitle("TODO")
            .withDescription("TP").build();
    public static final Note NOTE_EVENTS = new NoteBuilder().withTitle("Events")
            .withDescription("presentation...").build();

    private TypicalNotes() {} // prevents instantiation

    /**
     * Returns a {@code Notebook} with all the typical notes.
     */
    public static Notebook getTypicalNotebook() {
        Notebook notebook = new Notebook();
        for (Note note : getTypicalNotes()) {
            notebook.addNote(note);
        }
        return notebook;
    }

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(NOTE_EXISTENTIAL_CRISIS, NOTE_GRADUATION, NOTE_BUY_COFFEE));
    }
}
