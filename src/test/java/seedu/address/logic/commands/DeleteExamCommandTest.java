package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

public class DeleteExamCommandTest {
    private static final Index TEST_INDEX_FIRST_EXAM = INDEX_FIRST_PERSON;
    private static final Index TEST_INDEX_SECOND_EXAM = INDEX_SECOND_PERSON;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
    private Exam dummyExam = new Exam("Mid Year 2020", "26/7/2020", new Score("26/50"));

    @Test
    public void constructors_null_throwsNullPointerException() {

        // both arguments null
        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () ->
                new DeleteExamCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validStudentIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withExams(dummyExam).build();
        DeleteExamCommand deleteExamCommand =
                new DeleteExamCommand(INDEX_FIRST_PERSON, TEST_INDEX_FIRST_EXAM);
        Student expectedStudent = new StudentBuilder(ALICE).withExams().build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_EXAM_DELETED_SUCCESS,
                expectedStudent.getName(), dummyExam);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        DeleteExamCommand command = new DeleteExamCommand(outOfBoundsStudentIndex,
                TEST_INDEX_FIRST_EXAM);
        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidExamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsExamIndex = Index.fromOneBased(model.getSortedStudentList().get(0).getExams().size() + 1);
        DeleteExamCommand invalidCommand = new DeleteExamCommand(INDEX_FIRST_PERSON,
                outOfBoundsExamIndex);

        assertCommandFailure(invalidCommand, model, DeleteExamCommand.MESSAGE_MISSING_EXAM_INDEX);
    }

    @Test
    public void execute_validStudentIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withExams(dummyExam).build();
        model.setStudent(asker, clone);

        DeleteExamCommand command = new DeleteExamCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_FIRST_EXAM);
        Student expectedStudent = new StudentBuilder(BENSON).withExams().build();

        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_EXAM_DELETED_SUCCESS,
                clone.getName(), dummyExam);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsStudentIndex = INDEX_SECOND_PERSON;
        DeleteExamCommand invalidCommand = new DeleteExamCommand(outOfBoundsStudentIndex,
                TEST_INDEX_FIRST_EXAM);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidExamIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundsExamIndex = Index.fromOneBased(model.getSortedStudentList().get(0).getExams().size() + 1);
        DeleteExamCommand invalidCommand = new DeleteExamCommand(TEST_INDEX_FIRST_EXAM,
                outOfBoundsExamIndex);

        assertCommandFailure(invalidCommand, model, DeleteExamCommand.MESSAGE_MISSING_EXAM_INDEX);
    }

    @Test
    public void equals() {
        DeleteExamCommand deleteExamCommand =
                new DeleteExamCommand(INDEX_FIRST_PERSON, TEST_INDEX_FIRST_EXAM);

        // same object -> return true;
        assertEquals(deleteExamCommand, deleteExamCommand);

        // different object -> return false;
        assertNotEquals("hello", deleteExamCommand);

        // same fields -> return true;
        assertEquals(deleteExamCommand, new DeleteExamCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_FIRST_EXAM));

        // different student index -> return false;
        assertFalse(deleteExamCommand.equals(new DeleteExamCommand(INDEX_SECOND_PERSON,
                TEST_INDEX_FIRST_EXAM)));

        // different exam index -> return false;
        assertFalse(deleteExamCommand.equals(new DeleteExamCommand(INDEX_FIRST_PERSON,
                TEST_INDEX_SECOND_EXAM)));
    }
}
