package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DetailCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddDetailCommand;
import seedu.address.logic.commands.DeleteDetailCommand;
import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.EditDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.admin.Detail;

public class DetailCommandParser implements Parser<DetailCommand> {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public DetailCommand parse(String userInput)
            throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddDetailCommand.COMMAND_WORD:
            return parseAddDetailCommand(arguments);

        case DeleteDetailCommand.COMMAND_WORD:
            return parseDeleteDetailCommand(arguments);

        case EditDetailCommand.COMMAND_WORD:
            return parseEditDetailCommand(arguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    //@@author VaishakAnand
    private AddDetailCommand parseAddDetailCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEXT);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_TEXT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDetailCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDetailCommand.MESSAGE_USAGE), pe);
        }

        Detail detail = ParserUtil.parseDetail(argMultimap
                .getValue(PREFIX_TEXT).get());

        return new AddDetailCommand(index, detail);
    }

    private DeleteDetailCommand parseDeleteDetailCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDetailCommand.MESSAGE_USAGE));
        }

        Index studentIndex;
        Index detailIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            detailIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDetailCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteDetailCommand(studentIndex, detailIndex);
    }

    private EditDetailCommand parseEditDetailCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, COMMAND_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDetailCommand.MESSAGE_USAGE));
        }

        Index studentIndex;
        Index detailIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            detailIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDetailCommand.MESSAGE_USAGE), pe);
        }

        Detail detail = ParserUtil.parseDetail(argMultimap
                .getValue(PREFIX_TEXT).get());

        return new EditDetailCommand(studentIndex, detailIndex, detail);
    }

    private boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    //@@author

}
