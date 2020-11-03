package seedu.address.logic.commands;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.schedule.ScheduleViewMode;

public class ScheduleViewCommandTest {

    private Model model = new ModelManager();


    @Test
    public void execute_validViewModeAndDate_success() {
        ScheduleViewMode validViewMode = ScheduleViewMode.WEEKLY;
        LocalDate validDateTime = LocalDate.parse("2020-12-03");

        ScheduleViewCommand scheduleViewCommand = new ScheduleViewCommand(validViewMode, validDateTime);

        Model expectedModel = new ModelManager();
        expectedModel.setScheduleViewDate(validDateTime);
        expectedModel.setScheduleViewMode(validViewMode);

        CommandTestUtil.assertCommandSuccess(scheduleViewCommand, model,
                ScheduleCommand.COMMAND_SUCCESS_MESSAGE, expectedModel);
    }



}
