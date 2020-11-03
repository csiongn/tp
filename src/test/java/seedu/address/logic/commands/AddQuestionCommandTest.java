package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.AddQuestionCommand.MESSAGE_DUPLICATE_QUESTION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
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
import seedu.address.model.student.question.UnsolvedQuestion;
import seedu.address.testutil.StudentBuilder;

public class AddQuestionCommandTest {

    private static final String TEST_QUESTION = "What is 1 + 1?";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void constructor_null_throwsNullPointerException() {
        Index testIndex = INDEX_FIRST_PERSON;

        // both arguments null
        assertThrows(NullPointerException.class, () -> new AddQuestionCommand(null, null));

        // one argument null
        assertThrows(NullPointerException.class, () -> new AddQuestionCommand(testIndex, null));
        assertThrows(NullPointerException.class, () -> new AddQuestionCommand(null,
                new UnsolvedQuestion(TEST_QUESTION)));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions().build();
        UnsolvedQuestion question = new UnsolvedQuestion(TEST_QUESTION);
        AddQuestionCommand addQuestionCommand = new AddQuestionCommand(INDEX_FIRST_PERSON, question);
        Student expectedStudent = new StudentBuilder(ALICE).withQuestions(TEST_QUESTION).build();
        model.setStudent(asker, clone);

        String expectedMessage = String.format(AddQuestionCommand.MESSAGE_SUCCESS,
                clone.getName(), question);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addQuestionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        UnsolvedQuestion question = new UnsolvedQuestion(TEST_QUESTION);
        AddQuestionCommand invalidCommand = new AddQuestionCommand(outOfBounds, question);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unsolvedQuestionAlreadyExists_throwsCommandException() {
        Student asker = new StudentBuilder(ALICE).withQuestions(TEST_QUESTION).build();
        model.setStudent(model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased()), asker);

        AddQuestionCommand invalidCommand = new AddQuestionCommand(INDEX_FIRST_PERSON,
                new UnsolvedQuestion(TEST_QUESTION));

        assertCommandFailure(invalidCommand, model, MESSAGE_DUPLICATE_QUESTION);
    }

    @Test
    public void execute_solvedQuestionAlreadyExists_throwsCommandException() {
        Student asker = new StudentBuilder(ALICE).withSolved(DEFAULT_SOLUTION, TEST_QUESTION).build();
        model.setStudent(model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased()), asker);

        AddQuestionCommand invalidCommand = new AddQuestionCommand(INDEX_FIRST_PERSON,
                new UnsolvedQuestion(TEST_QUESTION));

        assertCommandFailure(invalidCommand, model, MESSAGE_DUPLICATE_QUESTION);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnsolvedQuestion question = new UnsolvedQuestion(TEST_QUESTION);
        Student clone = new StudentBuilder(asker).withQuestions().build();
        model.setStudent(asker, clone);

        AddQuestionCommand addQuestionCommand = new AddQuestionCommand(INDEX_FIRST_PERSON, question);
        Student expectedStudent = new StudentBuilder(BENSON).withQuestions(TEST_QUESTION).build();

        String expectedMessage = String.format(AddQuestionCommand.MESSAGE_SUCCESS,
                expectedStudent.getName(), question);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());
        expectedModel.setStudent(clone, expectedStudent);

        assertCommandSuccess(addQuestionCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        UnsolvedQuestion question = new UnsolvedQuestion(TEST_QUESTION);
        AddQuestionCommand invalidCommand = new AddQuestionCommand(INDEX_SECOND_PERSON, question);

        assertCommandFailure(invalidCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnsolvedQuestion testQuestion = new UnsolvedQuestion(TEST_QUESTION);
        AddQuestionCommand addQuestionCommand = new AddQuestionCommand(INDEX_FIRST_PERSON, testQuestion);

        // same object -> return true;
        assertTrue(addQuestionCommand.equals(addQuestionCommand));

        // same fields -> return true;
        assertTrue(addQuestionCommand.equals(new AddQuestionCommand(INDEX_FIRST_PERSON, testQuestion)));

        // different index -> return false;
        assertFalse(addQuestionCommand.equals(new AddQuestionCommand(INDEX_SECOND_PERSON, testQuestion)));

        // different question -> return false;
        UnsolvedQuestion altQuestion = new UnsolvedQuestion("Why do birds fly?");
        assertFalse(addQuestionCommand.equals(new AddQuestionCommand(INDEX_FIRST_PERSON, altQuestion)));
    }

}
