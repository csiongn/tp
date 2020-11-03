package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ATTENDANCE_COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class AttendanceCommandParser implements Parser<AttendanceCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    @Override
    public AttendanceCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddAttendanceCommand.COMMAND_WORD:
            return parseAddAttendanceCommand(arguments);

        case DeleteAttendanceCommand.COMMAND_WORD:
            return parseDeleteAttendanceCommand(arguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE));
        }
    }

    private AddAttendanceCommand parseAddAttendanceCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ATTENDANCE_COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, ATTENDANCE_COMMAND_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAttendanceCommand.MESSAGE_USAGE));
        }

        Index index;
        Attendance attendance;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            String attendanceDate = ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).get());
            String attendanceStatus = ParserUtil.parseAttendanceStatus(
                    argMultimap.getValue(PREFIX_ATTENDANCE_STATUS).get());
            Feedback attendanceFeedback = ParserUtil.parseFeedback(
                    argMultimap.getValue(PREFIX_ATTENDANCE_FEEDBACK).get());
            attendance = new Attendance(attendanceDate, attendanceStatus, attendanceFeedback);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAttendanceCommand.MESSAGE_USAGE), pe);
        }

        return new AddAttendanceCommand(index, attendance);
    }

    private DeleteAttendanceCommand parseDeleteAttendanceCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ATTENDANCE_COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_ATTENDANCE_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAttendanceCommand.MESSAGE_USAGE));
        }

        Index index;
        LocalDate lessonDate;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            String attendanceDate = ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_ATTENDANCE_DATE).get());
            lessonDate = Attendance.parseDate(attendanceDate);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAttendanceCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteAttendanceCommand(index, lessonDate);
    }

    private boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
