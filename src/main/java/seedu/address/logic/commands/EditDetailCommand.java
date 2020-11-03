package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Detail;

public class EditDetailCommand extends DetailCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = DetailCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": edits a Detail in the student identified "
            + "by the index number used in the displayed student list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_INDEX + "DETAIL_INDEX (must be a positive integer) "
            + PREFIX_TEXT + "DETAIL\n"
            + "Example: " + DetailCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_INDEX + "1 "
            + PREFIX_TEXT + "Eats sweets in class";

    public static final String MESSAGE_SUCCESS = "Detail edited for %s: %s";
    public static final String MESSAGE_BAD_DETAIL_INDEX = "There is no detail at this index";

    private static Logger logger = Logger.getLogger("Edit Detail Log");

    private final Index studentIndex;
    private final Index detailIndex;
    private final Detail detailToAdd;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     * @param studentIndex
     * @param detailIndex
     * @param detailToAdd
     */
    public EditDetailCommand(Index studentIndex, Index detailIndex, Detail detailToAdd) {
        requireAllNonNull(studentIndex, detailIndex, detailToAdd);
        this.studentIndex = studentIndex;
        this.detailIndex = detailIndex;
        this.detailToAdd = detailToAdd;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert(studentIndex != null && detailIndex != null && detailToAdd != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getSortedStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(studentIndex.getZeroBased());

        if (detailIndex.getZeroBased() >= studentToAddDetail.getDetails().size()) {
            logger.log(Level.WARNING, "Invalid detail index input error");
            throw new CommandException(MESSAGE_BAD_DETAIL_INDEX);
        }

        List<Detail> details = studentToAddDetail.getDetails();
        details.set(detailIndex.getZeroBased(), detailToAdd);

        Student updatedStudent = super.updateStudentDetail(studentToAddDetail, details);

        model.setStudent(studentToAddDetail, updatedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), detailToAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EditDetailCommand)) {
            return false;
        }

        EditDetailCommand other = (EditDetailCommand) obj;
        return studentIndex.equals(other.studentIndex) && detailIndex.equals(other.detailIndex)
                && detailToAdd.equals(other.detailToAdd);
    }
}
