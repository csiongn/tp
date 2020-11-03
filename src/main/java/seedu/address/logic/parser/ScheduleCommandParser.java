package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_MODE;

import java.time.LocalDate;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.ScheduleViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.ScheduleViewMode;


/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    /**
     * Parses the given String and returns a ScheduleCommand with the view mode and viewDateTime
     * @throws ParseException if the user input does not conform the expected date format
     */
    @Override
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VIEW_MODE, PREFIX_VIEW_DATE);


        if (argMultimap.getValue(PREFIX_VIEW_MODE).isEmpty()
                || argMultimap.getValue(PREFIX_VIEW_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleViewCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleViewCommand.MESSAGE_USAGE));
        }

        String viewDateTimeString = argMultimap.getValue(PREFIX_VIEW_DATE).get();
        LocalDate viewDate = (ParserUtil.parseViewDate(viewDateTimeString));

        String viewModeString = argMultimap.getValue(PREFIX_VIEW_MODE).get();
        ScheduleViewMode viewMode = (ParserUtil.parseViewMode(viewModeString));

        return new ScheduleViewCommand(viewMode, viewDate);
    }

}
