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
import seedu.address.model.student.admin.Detail;
import seedu.address.testutil.StudentBuilder;

public class AddDetailCommandTest {

    //@@author VaishakAnand
    private static final String TEST_DETAIL = "eats flies";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index testIndex = INDEX_FIRST_PERSON;

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new AddDetailCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new AddDetailCommand(testIndex, null));
        assertThrows(NullPointerException.class, () ->
                new AddDetailCommand(null, new Detail(TEST_DETAIL)));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails().build();
        Detail detail = new Detail(TEST_DETAIL);
        AddDetailCommand addAdditionalDetailCommand =
                new AddDetailCommand(INDEX_FIRST_PERSON, detail);
        Student expectedStudent = new StudentBuilder(ALICE).withDetails(TEST_DETAIL).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(AddDetailCommand.MESSAGE_SUCCESS, clone.getName(),
                detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addAdditionalDetailCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        AddDetailCommand command = new AddDetailCommand(outOfBounds,
                new Detail(TEST_DETAIL));

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Detail detail = new Detail(TEST_DETAIL);
        Student clone = new StudentBuilder(asker).withDetails().build();
        model.setStudent(asker, clone);

        AddDetailCommand command = new AddDetailCommand(INDEX_FIRST_PERSON, detail);
        Student expectedStudent = new StudentBuilder(BENSON).withDetails(TEST_DETAIL).build();

        String expectedMessage = String.format(AddDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBounds = INDEX_SECOND_PERSON;
        Detail detail = new Detail(TEST_DETAIL);
        AddDetailCommand invalidCommand = new AddDetailCommand(outOfBounds, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Detail testDetail = new Detail(TEST_DETAIL);
        AddDetailCommand addAdditionalDetailCommand =
                new AddDetailCommand(INDEX_FIRST_PERSON, testDetail);

        // same object -> return true;
        assertTrue(addAdditionalDetailCommand.equals(addAdditionalDetailCommand));

        // different object -> return false;
        assertFalse(addAdditionalDetailCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(addAdditionalDetailCommand.equals(new AddDetailCommand(INDEX_FIRST_PERSON, testDetail)));

        // different index -> return false;
        assertFalse(addAdditionalDetailCommand.equals(new AddDetailCommand(INDEX_SECOND_PERSON, testDetail)));

        // different detail -> return false;
        Detail altDetail = new Detail("he watches birds");
        assertFalse(addAdditionalDetailCommand.equals(new AddDetailCommand(INDEX_FIRST_PERSON, altDetail)));
    }

}
