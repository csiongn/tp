package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.ScheduleViewMode;

public class ScheduleCommandParserTest {

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ScheduleViewMode viewMode = ScheduleViewMode.WEEKLY;
        LocalDate viewDate = LocalDate.of(2020, 11, 5);

        assertParseSuccess(parser, " m/weekly d/5/11/2020",
                new ScheduleViewCommand(viewMode, viewDate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // date missing
        assertParseFailure(parser, " m/weekly", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleViewCommand.MESSAGE_USAGE));

        // both mode and date missing
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleViewCommand.MESSAGE_USAGE));

        // mode missing
        assertParseFailure(parser, " d/5/11/2020", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleViewCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid mode
        assertParseFailure(parser, " m/123 d/5/11/2020",
                ScheduleViewCommand.MESSAGE_INVALID_VIEW_MODE);

        //invalid date
        assertParseFailure(parser, " m/daily d/1234",
                ScheduleViewCommand.MESSAGE_INVALID_DATE);
    }

    @Test
    public void parseCommand_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleViewCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleViewCommand.MESSAGE_USAGE), () -> parser.parse("unknownCommand"));
    }
}
