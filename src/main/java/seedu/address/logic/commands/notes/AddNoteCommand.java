package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.note.Note;

public class AddNoteCommand extends NoteCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = NoteCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds a note to the notebook.\n\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION ";


    public static final String MESSAGE_SUCCESS = "New note added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the notebook";

    private final Note toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Note}
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd));
    }
}
