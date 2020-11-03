package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE_EVENTS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE_TODO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_RANDOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_RANDOM;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.note.Note;
import seedu.address.testutil.notes.EditNoteDescriptorBuilder;
import seedu.address.testutil.notes.NoteBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditNoteCommand.
 */
public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void execute_allFieldsSpecified_success() {
        Note editedNote = new NoteBuilder().build();
        EditNoteCommand.EditNoteDescriptor editNoteDescriptor =
                new EditNoteDescriptorBuilder(editedNote).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST, editNoteDescriptor);

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), model.getNotebook());
        expectedModel.setNote(model.getNotebook().getNotesList().get(INDEX_FIRST.getZeroBased()), editedNote);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastNote = Index.fromOneBased(model.getNotebook().getNotesList().size());
        Note lastNote = model.getNotebook().getNotesList().get(indexLastNote.getZeroBased());

        NoteBuilder noteInList = new NoteBuilder(lastNote);
        Note editedNote = noteInList.withTitle(VALID_TITLE_RANDOM)
                .withDescription(VALID_DESCRIPTION_RANDOM).build();

        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptorBuilder()
                .withTitle(VALID_TITLE_RANDOM).withDescription(VALID_DESCRIPTION_RANDOM).build();

        EditNoteCommand editNoteCommand = new EditNoteCommand(indexLastNote, editNoteDescriptor);

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS,
                editedNote);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                new Notebook(model.getNotebook()));
        expectedModel.setNote(lastNote, editedNote);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST,
                new EditNoteCommand.EditNoteDescriptor());
        Note editedNote = model.getNotebook().getNotesList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                new Notebook(model.getNotebook()));

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditNoteCommand standardCommand = new EditNoteCommand(INDEX_FIRST, DESC_NOTE_TODO);

        // same values -> returns true
        EditNoteCommand.EditNoteDescriptor copyNoteDescriptor =
                new EditNoteCommand.EditNoteDescriptor(DESC_NOTE_TODO);
        EditNoteCommand commandWithSameValues = new EditNoteCommand(INDEX_FIRST, copyNoteDescriptor);

        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new EditNoteCommand(INDEX_SECOND, DESC_NOTE_TODO), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditNoteCommand(INDEX_FIRST, DESC_NOTE_EVENTS), standardCommand);
    }
}
