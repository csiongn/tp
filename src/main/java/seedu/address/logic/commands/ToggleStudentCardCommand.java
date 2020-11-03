package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ToggleStudentCardCommand extends Command {

    public static final String COMMAND_WORD = "toggle";

    public static final String MESSAGE_TOGGLE_SUCCESS = "Display has been toggled";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_TOGGLE_SUCCESS, false, false, true, null);
    }
}
