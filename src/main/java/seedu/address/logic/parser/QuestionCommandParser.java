package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.QuestionCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Parses input arguments and creates a QuestionCommand.
 */
public class QuestionCommandParser implements Parser<QuestionCommand> {

    /**
     * Parses the given {@code String} in the context of a QuestionCommand
     * and returns a QuestionCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public QuestionCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddQuestionCommand.COMMAND_WORD:
            return parseAddQuestionCommand(arguments);

        case DeleteQuestionCommand.COMMAND_WORD:
            return parseDeleteQuestionCommand(arguments);

        case SolveQuestionCommand.COMMAND_WORD:
            return parseSolveQuestionCommand(arguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    private Index getStudentIndex(ArgumentMultimap argumentMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    private Index getQuestionIndex(ArgumentMultimap argumentMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    //======Parsers=====//

    private AddQuestionCommand parseAddQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, PREFIX_TEXT);

        if (areRequiredPrefixesMissing(argMultimap, PREFIX_TEXT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex = getStudentIndex(argMultimap);
        UnsolvedQuestion questionToAdd = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_TEXT).get());

        return new AddQuestionCommand(studentIndex, questionToAdd);
    }

    private SolveQuestionCommand parseSolveQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, COMMAND_PREFIXES);

        if (areRequiredPrefixesMissing(argMultimap, COMMAND_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex = getStudentIndex(argMultimap);
        Index questionIndex = getQuestionIndex(argMultimap);
        String solution = ParserUtil.parseSolution(argMultimap.getValue(PREFIX_TEXT).get());

        return new SolveQuestionCommand(studentIndex, questionIndex, solution);
    }

    private DeleteQuestionCommand parseDeleteQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, PREFIX_INDEX);

        if (areRequiredPrefixesMissing(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex = getStudentIndex(argMultimap);
        Index questionIndex = getQuestionIndex(argMultimap);

        return new DeleteQuestionCommand(studentIndex, questionIndex);
    }

    private boolean areRequiredPrefixesMissing(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return !Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
