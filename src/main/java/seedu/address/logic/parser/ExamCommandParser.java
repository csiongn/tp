package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ExamCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.EXAM_COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.ExamCommand;
import seedu.address.logic.commands.ExamStatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

/**
 * Parses input arguments and creates a new ExamCommand object.
 */
public class ExamCommandParser implements Parser<ExamCommand> {

    private static final String ERROR_ADD_EXAM =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE);
    private static final String ERROR_DEL_EXAM =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExamCommand.MESSAGE_USAGE);

    //@@author hogantan
    /**
     * Parses the given {@code String} of arguments in the context of the ExamCommand
     * and returns an ExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ExamCommand parse(String args) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddExamCommand.COMMAND_WORD:
            return parseAddExamCommand(arguments);

        case DeleteExamCommand.COMMAND_WORD:
            return parseDelExamCommand(arguments);

        case ExamStatsCommand.COMMAND_WORD:
            return parseExamStatsCommand(arguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddExamCommand
     * and returns an AddExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private AddExamCommand parseAddExamCommand(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, EXAM_COMMAND_PREFIXES);

        if (!arePrefixesPresent(argMultimap, EXAM_COMMAND_PREFIXES)) {
            throw new ParseException(ERROR_ADD_EXAM);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(ERROR_ADD_EXAM, pe);
        }

        String examName = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_EXAM_NAME).get());
        String examDate = ParserUtil.parseExamDate(argMultimap.getValue(PREFIX_EXAM_DATE).get());
        Score score = ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get().trim());

        Exam exam = new Exam(examName, examDate, score);
        return new AddExamCommand(index, exam);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExamCommand
     * and returns an DeleteExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private DeleteExamCommand parseDelExamCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXAM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXAM_INDEX)) {
            throw new ParseException(ERROR_DEL_EXAM);
        }

        Index studentIndex;
        Index examIndex;

        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            examIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EXAM_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(ERROR_DEL_EXAM, pe);
        }

        return new DeleteExamCommand(studentIndex, examIndex);
    }

    private ExamStatsCommand parseExamStatsCommand(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ExamStatsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExamStatsCommand.MESSAGE_USAGE), pe);
        }
    }
    //@@author
}
