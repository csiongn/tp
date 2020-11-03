package seedu.address.logic.parser.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ALL_NOTE_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.EditNoteCommand;
import seedu.address.logic.commands.notes.EditNoteCommand.EditNoteDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditNoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ALL_NOTE_PREFIXES);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        EditNoteDescriptor editNoteDescriptor = this.parseNote(argMultimap);

        if (!editNoteDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditNoteCommand.MESSAGE_NOT_EDITED);
        }

        return new EditNoteCommand(index, editNoteDescriptor);
    }

    /**
     * Parses given input note fields into discernible values.
     * @param argMultimap Tokenized input by user.
     * @return EditNoteDescriptor with parsed values to edit.
     * @throws ParseException if the user input does not conform the expected format
     */
    private EditNoteCommand.EditNoteDescriptor parseNote(ArgumentMultimap argMultimap) throws ParseException {
        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editNoteDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editNoteDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        return editNoteDescriptor;
    }

}
