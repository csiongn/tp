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
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.testutil.StudentBuilder;

public class AddAdditionalDetailCommandTest {

    private static final String TEST_DETAIL = "eats flies";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index testIndex = INDEX_FIRST_PERSON;

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new AddAdditionalDetailCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new AddAdditionalDetailCommand(testIndex, null));
        assertThrows(NullPointerException.class, () ->
                new AddAdditionalDetailCommand(null, new AdditionalDetail(TEST_DETAIL)));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withDetails().build();
        AdditionalDetail additionalDetail = new AdditionalDetail(TEST_DETAIL);
        AddAdditionalDetailCommand addAdditionalDetailCommand =
                new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, additionalDetail);
        Student expectedStudent = new StudentBuilder(ALICE).withDetails(TEST_DETAIL).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(AddAdditionalDetailCommand.MESSAGE_SUCCESS, clone.getName(),
                additionalDetail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addAdditionalDetailCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddAdditionalDetailCommand command = new AddAdditionalDetailCommand(outOfBounds,
                new AdditionalDetail(TEST_DETAIL));

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        AdditionalDetail detail = new AdditionalDetail(TEST_DETAIL);
        Student clone = new StudentBuilder(asker).withDetails().build();
        model.setStudent(asker, clone);

        AddAdditionalDetailCommand command = new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, detail);
        Student expectedStudent = new StudentBuilder(BENSON).withDetails(TEST_DETAIL).build();

        String expectedMessage = String.format(AddAdditionalDetailCommand.MESSAGE_SUCCESS,
                clone.getName(), detail);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBounds = INDEX_SECOND_PERSON;
        AdditionalDetail detail = new AdditionalDetail(TEST_DETAIL);
        AddAdditionalDetailCommand invalidCommand = new AddAdditionalDetailCommand(outOfBounds, detail);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AdditionalDetail testDetail = new AdditionalDetail(TEST_DETAIL);
        AddAdditionalDetailCommand addAdditionalDetailCommand =
                new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, testDetail);

        // same object -> return true;
        assertTrue(addAdditionalDetailCommand.equals(addAdditionalDetailCommand));

        // different object -> return false;
        assertFalse(addAdditionalDetailCommand.equals("hello"));

        // same fields -> return true;
        assertTrue(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, testDetail)));

        // different index -> return false;
        assertFalse(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_SECOND_PERSON, testDetail)));

        // different detail -> return false;
        AdditionalDetail altDetail = new AdditionalDetail("he watches birds");
        assertFalse(addAdditionalDetailCommand.equals(new AddAdditionalDetailCommand(INDEX_FIRST_PERSON, altDetail)));
    }

}
