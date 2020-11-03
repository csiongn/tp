package seedu.address.logic.commands;


/**
 * Finds and list all students with classes on a given date.
 * This is also the schedule of the user on that particular date.
 */
public abstract class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String COMMAND_SUCCESS_MESSAGE = "Here is your schedule";

}
