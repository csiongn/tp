package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.TEST_QUESTIONS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteQuestionCommand.MESSAGE_BAD_QUESTION_INDEX;
import static seedu.address.logic.commands.DeleteQuestionCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.question.Question;
import seedu.address.testutil.StudentBuilder;

public class DeleteQuestionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index questionIndex = Index.fromOneBased(1);

        // both null
        assertThrows(NullPointerException.class, () -> new DeleteQuestionCommand(null, null));

        // one null
        assertThrows(NullPointerException.class, () -> new DeleteQuestionCommand(null, questionIndex));
        assertThrows(NullPointerException.class, () -> new DeleteQuestionCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndicesUnsolved_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(TEST_QUESTIONS).build();
        model.setStudent(asker, clone);

        Index questionIndex = Index.fromOneBased(1);
        Question removed = clone.getQuestions().get(questionIndex.getZeroBased());

        Student expected = deleteQuestion(questionIndex, clone);
        Model expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expected);

        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, questionIndex);
        String expectedMessage = String.format(MESSAGE_SUCCESS, expected.getName(), removed);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesSolved_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withSolved(DEFAULT_SOLUTION, TEST_QUESTIONS).build();
        model.setStudent(asker, clone);

        Index questionIndex = Index.fromOneBased(2);
        Question removed = clone.getQuestions().get(questionIndex.getZeroBased());

        Student expected = deleteQuestion(questionIndex, clone);
        Model expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expected);

        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, questionIndex);
        String expectedMessage = String.format(MESSAGE_SUCCESS, expected.getName(), removed);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withSolved(DEFAULT_SOLUTION, TEST_QUESTIONS).build();
        model.setStudent(asker, clone);
        Index questionIndex = Index.fromOneBased(2);
        Question removed = clone.getQuestions().get(questionIndex.getZeroBased());

        Student expected = deleteQuestion(questionIndex, clone);
        Model expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expected);

        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, questionIndex);
        String expectedMessage = String.format(MESSAGE_SUCCESS, expected.getName(), removed);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromZeroBased(model.getSortedStudentList().size());
        Index question = Index.fromZeroBased(0);
        DeleteQuestionCommand invalidCommand = new DeleteQuestionCommand(outOfBounds, question);
        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index question = Index.fromZeroBased(0);
        DeleteQuestionCommand invalidCommand = new DeleteQuestionCommand(INDEX_SECOND_PERSON, question);
        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidQuestionIndex_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withSolved(DEFAULT_SOLUTION, TEST_QUESTIONS).build();
        model.setStudent(asker, clone);

        Index questionIndex = Index.fromZeroBased(TEST_QUESTIONS.length);
        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, questionIndex);

        assertCommandFailure(command, model, MESSAGE_BAD_QUESTION_INDEX);
    }

    @Test
    public void equals() {
        Index question = Index.fromOneBased(1);
        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, question);

        // same obj -> return true
        assertTrue(command.equals(command));

        // same fields -> return true
        assertTrue(command.equals(new DeleteQuestionCommand(INDEX_FIRST_PERSON, question)));

        // different fields -> return false
        assertFalse(command.equals(new DeleteQuestionCommand(INDEX_SECOND_PERSON, question)));
        assertFalse(command.equals(new DeleteQuestionCommand(INDEX_FIRST_PERSON, Index.fromOneBased(2))));
    }

    private Student deleteQuestion(Index index, Student toCopy) {
        List<Question> questions = new ArrayList<>(toCopy.getQuestions());
        questions.remove(index.getZeroBased());
        return new Student(toCopy.getName(), toCopy.getPhone(), toCopy.getSchool(),
                toCopy.getYear(), toCopy.getAdmin(), questions, toCopy.getAcademic());
    }
}
