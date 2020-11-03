package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.testutil.StudentBuilder;

public class AddAttendanceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
    private final Attendance validAttendance = new Attendance("12/02/2020", "present",
            new Feedback("sleepy"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index testIndex = INDEX_FIRST_PERSON;

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(testIndex, null));
        assertThrows(NullPointerException.class, () ->
                new AddAttendanceCommand(null, validAttendance));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withAttendances().build();
        AddAttendanceCommand addAttendanceCommand =
                new AddAttendanceCommand(INDEX_FIRST_PERSON, validAttendance);
        Student expectedStudent = new StudentBuilder(ALICE).withAttendances(validAttendance).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(AddAttendanceCommand.MESSAGE_SUCCESS, clone.getName(),
                validAttendance);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        AddAttendanceCommand command =
                new AddAttendanceCommand(outOfBounds, validAttendance);

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withAttendances().build();

        model.setStudent(asker, clone);

        AddAttendanceCommand command = new AddAttendanceCommand(INDEX_FIRST_PERSON, validAttendance);
        Student expectedStudent = new StudentBuilder(BENSON).withAttendances(validAttendance).build();

        String expectedMessage = String.format(AddAttendanceCommand.MESSAGE_SUCCESS, clone.getName(),
                validAttendance);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBounds = INDEX_SECOND_PERSON;
        AddAttendanceCommand command =
                new AddAttendanceCommand(outOfBounds, validAttendance);

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddAttendanceCommand addAttendanceCommand = new AddAttendanceCommand(INDEX_FIRST_PERSON, validAttendance);

        // same object -> return true;
        assertTrue(addAttendanceCommand.equals(addAttendanceCommand));

        // different object -> return false;
        assertFalse(addAttendanceCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(addAttendanceCommand.equals(new AddAttendanceCommand(INDEX_FIRST_PERSON, validAttendance)));

        // different index -> return false;
        assertFalse(addAttendanceCommand.equals(new AddAttendanceCommand(INDEX_SECOND_PERSON, validAttendance)));

        // different attendance -> return false;
        Attendance altAttendance = new Attendance("17/02/2020", "absent", new Feedback("sleepy"));
        assertFalse(addAttendanceCommand.equals(new AddAttendanceCommand(INDEX_FIRST_PERSON, altAttendance)));
    }
}
