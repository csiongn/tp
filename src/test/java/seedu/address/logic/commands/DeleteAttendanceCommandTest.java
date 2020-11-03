package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteAttendanceCommand.MESSAGE_INVALID_ATTENDANCE_DATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.testutil.StudentBuilder;

public class DeleteAttendanceCommandTest {

    private static Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
    private static final Index TEST_INDEX_FIRST_STUDENT = INDEX_FIRST_PERSON;
    private static final LocalDate TEST_DATE = model.getFilteredStudentList().get(0)
            .getAttendance().get(0).getLessonDate();
    private static final String USER_INPUT_DATE = model.getFilteredStudentList().get(0)
            .getAttendance().get(0).getUserInputDate();
    private final Attendance validAttendance = new Attendance(USER_INPUT_DATE, "present",
            new Feedback("sleepy"));

    @Test
    public void constructor_null_throwsNullPointerException() {

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new DeleteAttendanceCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new DeleteAttendanceCommand(null, TEST_DATE));
        assertThrows(NullPointerException.class, () ->
                new DeleteAttendanceCommand(TEST_INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void execute_validStudentIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withAttendances(validAttendance).build();
        DeleteAttendanceCommand deleteAttendanceCommand =
                new DeleteAttendanceCommand(TEST_INDEX_FIRST_STUDENT, TEST_DATE);
        Student expectedStudent = new StudentBuilder(ALICE).withAttendances().build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(DeleteAttendanceCommand.MESSAGE_SUCCESS,
                clone.getName(), USER_INPUT_DATE);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(deleteAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        DeleteAttendanceCommand command = new DeleteAttendanceCommand(outOfBoundsStudentIndex,
                TEST_DATE);
        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDateUnfilteredList_throwsCommandException() {
        LocalDate invalidDate = TEST_DATE.plusDays(1);
        DeleteAttendanceCommand invalidCommand = new DeleteAttendanceCommand(TEST_INDEX_FIRST_STUDENT,
                invalidDate);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_ATTENDANCE_DATE);
    }

    @Test
    public void execute_validStudentIndexFilteredList_success() {
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
        showPersonAtIndex(newModel, INDEX_SECOND_PERSON);

        Student asker = newModel.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withAttendances(validAttendance).build();
        newModel.setStudent(asker, clone);

        DeleteAttendanceCommand command = new DeleteAttendanceCommand(INDEX_FIRST_PERSON,
                TEST_DATE);
        Student expectedStudent = new StudentBuilder(BENSON).withAttendances().build();

        String expectedMessage = String.format(DeleteAttendanceCommand.MESSAGE_SUCCESS,
                clone.getName(), USER_INPUT_DATE);

        ModelManager expectedModel = new ModelManager(newModel.getReeve(), new UserPrefs(), newModel.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, newModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsStudentIndex = INDEX_SECOND_PERSON;
        DeleteAttendanceCommand invalidCommand = new DeleteAttendanceCommand(outOfBoundsStudentIndex,
                TEST_DATE);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDateFilteredList_throwsCommandException() {
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
        showPersonAtIndex(newModel, INDEX_SECOND_PERSON);

        LocalDate invalidDate = TEST_DATE.minusMonths(1);
        DeleteAttendanceCommand invalidCommand =
                new DeleteAttendanceCommand(TEST_INDEX_FIRST_STUDENT, invalidDate);

        assertCommandFailure(invalidCommand, newModel, MESSAGE_INVALID_ATTENDANCE_DATE);
    }

    @Test
    public void equals() {
        DeleteAttendanceCommand deleteAdditionalDetailCommand =
                new DeleteAttendanceCommand(INDEX_FIRST_PERSON, TEST_DATE);

        // same object -> return true;
        assertTrue(deleteAdditionalDetailCommand.equals(deleteAdditionalDetailCommand));

        // different object -> return false;
        assertFalse(deleteAdditionalDetailCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(deleteAdditionalDetailCommand.equals(new DeleteAttendanceCommand(INDEX_FIRST_PERSON,
                TEST_DATE)));

        // different student index -> return false;
        assertFalse(deleteAdditionalDetailCommand.equals(new DeleteAttendanceCommand(INDEX_SECOND_PERSON,
                TEST_DATE)));

        // different date -> return false;
        LocalDate altDate = TEST_DATE.plusDays(1);
        assertFalse(deleteAdditionalDetailCommand.equals(new DeleteAttendanceCommand(INDEX_FIRST_PERSON,
                altDate)));
    }
}
