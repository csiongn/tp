package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FEEDBACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_STATUS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class AttendanceCommandParserTest {
    private static final String ADD_ATTENDANCE_DESC = AddAttendanceCommand.COMMAND_WORD + " ";
    private static final String DELETE_ATTENDANCE_DESC = DeleteAttendanceCommand.COMMAND_WORD + " ";
    private static final Attendance VALID_ATTENDANCE = new Attendance(VALID_ATTENDANCE_DATE_AMY,
            VALID_ATTENDANCE_STATUS_AMY, new Feedback(VALID_ATTENDANCE_FEEDBACK_AMY));
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private final AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parse_addAttendanceAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String userInput = ADD_ATTENDANCE_DESC + targetStudentIndex.getOneBased() + ATTENDANCE_DESC_AMY;
        AddAttendanceCommand expectedCommand = new AddAttendanceCommand(targetStudentIndex, VALID_ATTENDANCE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addAttendanceMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAttendanceCommand.MESSAGE_USAGE);
        Index targetStudentIndex = INDEX_SECOND_PERSON;

        // missing index and prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC, expectedMessage);

        // valid index, missing prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + targetStudentIndex + " ", expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, ADD_ATTENDANCE_DESC + ATTENDANCE_DESC_AMY, expectedMessage);
    }

    @Test
    public void parse_deleteDetailAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String targetAttendanceDate = " " + PREFIX_ATTENDANCE_DATE + VALID_ATTENDANCE_DATE_AMY;
        String userInput = DELETE_ATTENDANCE_DESC + targetStudentIndex.getOneBased() + targetAttendanceDate;
        DeleteAttendanceCommand expectedCommand = new DeleteAttendanceCommand(targetStudentIndex,
                LocalDate.parse(VALID_ATTENDANCE_DATE_AMY, FORMATTER));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteDetailMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAttendanceCommand.MESSAGE_USAGE);

        // missing 2 arguments
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC, expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + "2", expectedMessage);

        String targetAttendanceDate = String.format(" %s%s", PREFIX_ATTENDANCE_DATE,
                LocalDate.parse(VALID_ATTENDANCE_DATE_AMY, FORMATTER));
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + targetAttendanceDate, expectedMessage);

        // wrong date format
        String invalidDate = " " + PREFIX_ATTENDANCE_DATE + "2020-03-22";
        assertParseFailure(parser, DELETE_ATTENDANCE_DESC + "2" + invalidDate, expectedMessage);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendanceCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse("unknownCommand"));
    }
}
