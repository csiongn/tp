package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class ExamStatsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void execute_examStats_success() {
        Student asker = model.getSortedStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).build();

        CommandResult expectedCommandResult =
                new CommandResult(ExamStatsCommand.MESSAGE_EXAM_STATS_SUCCESS, false,
                        false, false, clone);
        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());

        assertCommandSuccess(new ExamStatsCommand(INDEX_FIRST_PERSON), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsStudentIndex = Index.fromOneBased(model.getSortedStudentList().size() + 1);
        ExamStatsCommand command = new ExamStatsCommand(outOfBoundsStudentIndex);

        assertCommandFailure(command, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
