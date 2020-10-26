package seedu.address.logic.parser.notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.notes.AddNoteCommand;
import seedu.address.logic.commands.notes.DeleteNoteCommand;
import seedu.address.logic.commands.notes.EditNoteCommand;
import seedu.address.logic.commands.notes.NoteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class NotebookParser implements Parser<NoteCommand> {

    /**
     * Used for initial separation of note command word and args.
     */
    private static final Pattern BASIC_NOTE_COMMAND_FORMAT =
            Pattern.compile("(?<noteCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into specific note command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_NOTE_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("noteCommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            return new EditNoteCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
