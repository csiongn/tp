package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.note.Note;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNoteCommand}.
 */
public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void execute_validIndex_success() {
        Note noteToDelete = model.getNotebook().getNotesList().get(INDEX_FIRST.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                model.getNotebook());
        expectedModel.deleteNote(noteToDelete);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getNotebook().getNotesList().size() + 1);
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteNoteCommand deleteFirstCommand = new DeleteNoteCommand(INDEX_FIRST);
        DeleteNoteCommand deleteSecondCommand = new DeleteNoteCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteNoteCommand deleteFirstCommandCopy = new DeleteNoteCommand(INDEX_FIRST_PERSON);
        assertEquals(deleteFirstCommandCopy, deleteFirstCommand);

        // different types -> returns false
        assertNotEquals(deleteFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);

        // different person -> returns false
        assertNotEquals(deleteSecondCommand, deleteFirstCommand);
    }
}
