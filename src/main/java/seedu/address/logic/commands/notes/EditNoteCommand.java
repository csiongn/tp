package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;

/**
 * Edits the details of an existing note in the notebook
 */
public class EditNoteCommand extends NoteCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = NoteCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Edits the details of the note identified "
            + "by the index number used in the displayed notebook. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] ";

    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited Note:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the notebook.";


    private final Index index;
    private final EditNoteCommand.EditNoteDescriptor editNoteDescriptor;

    /**
     * @param index of the note in the student list to edit
     * @param editNoteDescriptor details to edit the student with
     */
    public EditNoteCommand(Index index, EditNoteCommand.EditNoteDescriptor editNoteDescriptor) {
        requireNonNull(index);
        requireNonNull(editNoteDescriptor);

        this.index = index;
        this.editNoteDescriptor = new EditNoteCommand.EditNoteDescriptor(editNoteDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Note> lastShownList = model.getNotebook().getNotesList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToEdit = lastShownList.get(index.getZeroBased());
        Note editedNote = createEditedNote(noteToEdit, editNoteDescriptor);

        if (!noteToEdit.isSameNote(editedNote) && model.hasNote(editedNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.setNote(noteToEdit, editedNote);
        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, editedNote));
    }

    /**
     * Creates and returns a {@code Note} with the details of {@code noteToEdit}
     * edited with {@code editNoteDescriptor}.
     */
    private static Note createEditedNote(Note noteToEdit, EditNoteCommand.EditNoteDescriptor editNoteDescriptor) {
        assert noteToEdit != null;

        Title updatedTitle = editNoteDescriptor.getTitle().orElse(noteToEdit.getTitle());
        Description updatedDescription = editNoteDescriptor.getDescription().orElse(noteToEdit.getDescription());

        return new Note(updatedTitle, updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteCommand)) {
            return false;
        }

        // state check
        EditNoteCommand e = (EditNoteCommand) other;
        return index.equals(e.index)
                && editNoteDescriptor.equals(e.editNoteDescriptor);
    }

    /**
     * Stores the details to edit the note with. Each non-empty field value will replace the
     * corresponding field value of the note.
     */
    public static class EditNoteDescriptor {

        private Title title;
        private Description description;

        public EditNoteDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditNoteDescriptor(EditNoteCommand.EditNoteDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditNoteCommand.EditNoteDescriptor)) {
                return false;
            }

            // state check
            EditNoteCommand.EditNoteDescriptor e = (EditNoteCommand.EditNoteDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription());
        }
    }

}
