package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model, AddExamCommand and DeleteExamCommand)
 * and unit tests for ExamCommand
 */
public class AddExamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
    private Exam dummyExam = new Exam("Mid Year 2020", "26/7/2020", new Score("26/50"));

    @Test
    public void constructors_null_throwsNullPointerException() {

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new AddExamCommand(null, null));

        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new AddExamCommand(INDEX_FIRST_PERSON, null));
        assertThrows(NullPointerException.class, () ->
                new AddExamCommand(null, dummyExam));

        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withExams().build();
        AddExamCommand addExamCommand =
                new AddExamCommand(INDEX_FIRST_PERSON, dummyExam);
        Student expectedStudent = new StudentBuilder(ALICE).withExams(dummyExam).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(AddExamCommand.MESSAGE_EXAM_ADDED_SUCCESS, expectedStudent.getName(),
                dummyExam);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        AddExamCommand command = new AddExamCommand(outOfBounds,
                dummyExam);

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withExams().build();
        model.setStudent(asker, clone);

        AddExamCommand command = new AddExamCommand(INDEX_FIRST_PERSON, dummyExam);
        Student expectedStudent = new StudentBuilder(BENSON).withExams(dummyExam).build();

        String expectedMessage = String.format(AddExamCommand.MESSAGE_EXAM_ADDED_SUCCESS,
                expectedStudent.getName(), dummyExam);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBounds = INDEX_SECOND_PERSON;
        AddExamCommand invalidCommand = new AddExamCommand(outOfBounds, dummyExam);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddExamCommand addExamCommand =
                new AddExamCommand(INDEX_FIRST_PERSON, dummyExam);

        // same object -> return true;
        assertEquals(addExamCommand, addExamCommand);

        // different object -> return false;
        assertNotEquals("hello", addExamCommand);

        // same fields -> return true;
        assertEquals(addExamCommand, new AddExamCommand(INDEX_FIRST_PERSON, dummyExam));

        // different index -> return false;
        assertNotEquals(addExamCommand, new AddExamCommand(INDEX_SECOND_PERSON, dummyExam));

        // different exam -> return false;
        Exam altExam = new Exam("Alt Exam", "12/12/2020", new Score("1/1"));
        assertNotEquals(addExamCommand, new AddExamCommand(INDEX_FIRST_PERSON, altExam));
    }
}
