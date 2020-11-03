package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ToggleStudentCardCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_toggle_success() {
        CommandResult expectedCommandResult =
                new CommandResult(ToggleStudentCardCommand.MESSAGE_TOGGLE_SUCCESS, false,
                        false, true, null);
        assertCommandSuccess(new ToggleStudentCardCommand(), model, expectedCommandResult, expectedModel);
    }
}
