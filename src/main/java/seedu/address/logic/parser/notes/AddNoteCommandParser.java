package seedu.address.logic.parser.notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ALL_NOTE_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.notes.AddNoteCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNOteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ALL_NOTE_PREFIXES);

        if (!arePrefixesPresent(argMultimap, ALL_NOTE_PREFIXES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Note note = new Note(title, description);
        return new AddNoteCommand(note);
    }

}
