package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteDetailCommand.MESSAGE_BAD_DETAIL_INDEX;
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
import seedu.address.model.student.admin.Detail;
import seedu.address.testutil.StudentBuilder;

public class DeleteDetailCommandTest {

    //@@author VaishakAnand
    private static final Index TEST_INDEX_FIRST_STUDENT = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_STUDENT = INDEX_SECOND_PERSON;
    private static final Index TEST_INDEX_FIRST_DETAIL = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_DETAIL = INDEX_SECOND_PERSON;
    private static final String TEST_DETAIL = "eats flies";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new DeleteDetailCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new DeleteDetailCommand(null, TEST_INDEX_FIRST_STUDENT));
        assertThrows(NullPointerException.class, () ->
                new DeleteDetailCommand(TEST_INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void execute_validStudentIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails(TEST_DETAIL).build();
        Detail detail = new Detail(TEST_DETAIL);
        DeleteDetailCommand deleteAdditionalDetailCommand =
                new DeleteDetailCommand(TEST_INDEX_FIRST_STUDENT, TEST_INDEX_FIRST_DETAIL);
        Student expectedStudent = new StudentBuilder(ALICE).withDetails().build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(DeleteDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(deleteAdditionalDetailCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        DeleteDetailCommand command = new DeleteDetailCommand(outOfBoundsStudentIndex,
                TEST_INDEX_FIRST_DETAIL);
        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDetailIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsDetailIndex =
                Index.fromOneBased(model.getSortedStudentList().get(0).getDetails().size() + 1);
        DeleteDetailCommand invalidCommand = new DeleteDetailCommand(TEST_INDEX_FIRST_STUDENT,
                outOfBoundsDetailIndex);

        assertCommandFailure(invalidCommand, model, MESSAGE_BAD_DETAIL_INDEX);
    }

    @Test
    public void execute_validStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Detail detail = new Detail(TEST_DETAIL);
        Student clone = new StudentBuilder(asker).withDetails(TEST_DETAIL).build();
        model.setStudent(asker, clone);

        DeleteDetailCommand command = new DeleteDetailCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_FIRST_DETAIL);
        Student expectedStudent = new StudentBuilder(BENSON).withDetails().build();

        String expectedMessage = String.format(DeleteDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsStudentIndex = INDEX_SECOND_PERSON;
        DeleteDetailCommand invalidCommand = new DeleteDetailCommand(outOfBoundsStudentIndex,
                TEST_INDEX_FIRST_DETAIL);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidDetailIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsDetailIndex =
                Index.fromOneBased(model.getSortedStudentList().get(0).getDetails().size() + 1);
        DeleteDetailCommand invalidCommand =
                new DeleteDetailCommand(TEST_INDEX_FIRST_STUDENT, outOfBoundsDetailIndex);

        assertCommandFailure(invalidCommand, model, MESSAGE_BAD_DETAIL_INDEX);
    }

    @Test
    public void equals() {
        Detail testDetail = new Detail(TEST_DETAIL);
        DeleteDetailCommand deleteAdditionalDetailCommand =
                new DeleteDetailCommand(INDEX_FIRST_PERSON, TEST_INDEX_FIRST_DETAIL);

        // same object -> return true;
        assertTrue(deleteAdditionalDetailCommand.equals(deleteAdditionalDetailCommand));

        // different object -> return false;
        assertFalse(deleteAdditionalDetailCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(deleteAdditionalDetailCommand.equals(new DeleteDetailCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_FIRST_DETAIL)));

        // different student index -> return false;
        assertFalse(deleteAdditionalDetailCommand.equals(new AddDetailCommand(INDEX_SECOND_PERSON,
                testDetail)));

        // different detail index -> return false;
        Detail altDetail = new Detail("he watches birds");
        assertFalse(deleteAdditionalDetailCommand.equals(new DeleteDetailCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_SECOND_DETAIL)));
    }
}
