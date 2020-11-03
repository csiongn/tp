package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.note.Note;

/**
 * Deletes a student identified using it's displayed index from Reeve.
 */
public class DeleteNoteCommand extends NoteCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = NoteCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Deletes the note identified by the index number used in the displayed notebook.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Note: %1$s";

    private final Index targetIndex;

    public DeleteNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Note> lastShownList = model.getNotebook().getNotesList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteNote(noteToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
