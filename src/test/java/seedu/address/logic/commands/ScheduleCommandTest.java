package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Reeve;
import seedu.address.model.UserPrefs;

public class ScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void equals() {
        LocalDate date1 = LocalDate.of(2020, 11, 3);
        LocalDate date2 = LocalDate.of(2020, 11, 4);

        ScheduleCommand scheduleCommand1 = new ScheduleCommand(date1);
        ScheduleCommand scheduleCommand2 = new ScheduleCommand(date2);

        // same object -> true
        assertTrue(scheduleCommand1.equals(scheduleCommand1));

        // different object -> false
        assertFalse(scheduleCommand1.equals(scheduleCommand2));

        // same date return true
        ScheduleCommand scheduleCommand1Copy = new ScheduleCommand(date1);
        assertTrue(scheduleCommand1.equals(scheduleCommand1Copy));

        // null return false
        assertFalse(scheduleCommand1.equals(null));
    }

    @Test
    public void execute_validDate_success() {
        LocalDate validDate = LocalDate.of(2020, 11, 3);
        DayOfWeek day = validDate.getDayOfWeek();

        Model expectedModel = new ModelManager(new Reeve(model.getReeve()), new UserPrefs(), getTypicalNotebook());
        expectedModel.updateFilteredStudentList(std -> std.getAdmin().getClassTime().isSameDay(day));

        String expectedMsg = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                expectedModel.getFilteredStudentList().size());

        ScheduleCommand scheduleCommand = new ScheduleCommand(validDate);

        assertCommandSuccess(scheduleCommand, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_nullDateToFindSchedule_throwsNullPointerException() {
        ScheduleCommand scheduleCommand = new ScheduleCommand(null);
        assertThrows(NullPointerException.class, () -> scheduleCommand.execute(model));
    }


}
