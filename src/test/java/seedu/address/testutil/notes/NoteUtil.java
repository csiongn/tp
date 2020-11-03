package seedu.address.testutil.notes;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.notes.EditNoteCommand.EditNoteDescriptor;

/**
 * A utility class for Note.
 */
public class NoteUtil {

    /**
     * Returns the part of command string for the given {@code EditNoteDescriptor}'s details.
     */
    public static String getEditNoteDescriptorDetails(EditNoteDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.title).append(" "));
        descriptor.getDescription().ifPresent(description ->
                sb.append(PREFIX_DESCRIPTION).append(description.description).append(" "));
        return sb.toString();
    }
}
