package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.notes.JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_GRADUATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Title;

public class JsonAdaptedNoteTest {
    private static final String INVALID_TITLE = "Lorem ipsum dolor sit amet hehe";
    private static final String INVALID_DESCRIPTION = "Lorem ipsum dolor sit amet"
            + ", consectetur adipiscing elit. Integer dapibus sapienar";

    private static final String VALID_TITLE = NOTE_GRADUATION.getTitle().toString();
    private static final String VALID_DESCRIPTION = NOTE_GRADUATION.getDescription().toString();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedNote note = new JsonAdaptedNote(NOTE_GRADUATION);
        assertEquals(NOTE_GRADUATION, note.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(INVALID_TITLE, VALID_DESCRIPTION);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(VALID_TITLE, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote(VALID_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

}
