package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_SORTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.GEORGE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.NameComparator;
import seedu.address.model.student.YearComparator;
import seedu.address.model.student.admin.ClassTimeComparator;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    private SortCommand sortByName = new SortCommand("name");
    private SortCommand sortByNameDuplicate = new SortCommand("name");
    private SortCommand sortByClassTime = new SortCommand("classTime");
    private SortCommand sortByYear = new SortCommand("year");

    @Test
    public void equals() {
        assertEquals(sortByName, sortByNameDuplicate);
        assertNotEquals(sortByName, sortByClassTime);
        assertNotEquals(sortByName, sortByYear);
    }

    @Test
    public void constructor_invalidComparisonMeans_throwException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_sortByName_sorted() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
        expectedModel.updateSortedStudentList(new NameComparator());
        String expectedMessage = String.format(MESSAGE_STUDENTS_SORTED, "name");

        SortCommand command = new SortCommand("name");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getSortedStudentList());
    }

    @Test
    public void execute_sortByClassTime_sorted() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
        expectedModel.updateSortedStudentList(new ClassTimeComparator());
        String expectedMessage = String.format(MESSAGE_STUDENTS_SORTED, "classTime");

        SortCommand command = new SortCommand("classTime");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE, GEORGE, BENSON, FIONA, ALICE), model.getSortedStudentList());
    }

    @Test
    public void execute_sortByYear_sorted() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
        expectedModel.updateSortedStudentList(new YearComparator());
        String expectedMessage = String.format(MESSAGE_STUDENTS_SORTED, "year");

        SortCommand command = new SortCommand("year");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, BENSON, FIONA, ALICE, GEORGE, CARL, ELLE), model.getSortedStudentList());
    }
}
