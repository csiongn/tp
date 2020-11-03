package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.ScheduleViewMode;

public class ScheduleViewCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_INVALID_VIEW_MODE = "Invalid view mode!";
    public static final String MESSAGE_INVALID_DATE = "Invalid date!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n"
            + "m/[weekly / daily]\n"
            + "d/[dd/mm/yyyy]\n"
            + "Example: schedule m/weekly d/2/11/2020";

    private final LocalDate viewDate;
    private final ScheduleViewMode viewMode;

    /**
     * Creates a command to view schedule.
     * @param scheduleViewMode mode to view either weekly or daily.
     * @param viewDate the date to view.
     */
    public ScheduleViewCommand(ScheduleViewMode scheduleViewMode, LocalDate viewDate) {
        super();
        requireAllNonNull(scheduleViewMode, viewDate);
        this.viewMode = scheduleViewMode;
        this.viewDate = viewDate;
    }

    /**
     * Execute and returns CommandResult for UI.
     * UI will then change according to the view mode and date.
     * @throws CommandException for invalid view modes or invalid date format
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setScheduleViewDate(viewDate);
        model.setScheduleViewMode(viewMode);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateClassTimesToEvent();
        return new CommandResult(COMMAND_SUCCESS_MESSAGE, false, false, true, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true; // short circuit if same object
        }

        if (obj instanceof ScheduleViewCommand) {
            ScheduleViewCommand other = (ScheduleViewCommand) obj;
            return other.viewDate.equals(viewDate) && other.viewMode.equals(viewMode);
        }
        return false;

    }
}
