package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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

public class AddDetailCommand extends DetailCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = DetailCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": adds a Detail to the student identified "
            + "by the index number used in the displayed student list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_TEXT + "DETAIL\n"
            + "Example: " + DetailCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_TEXT + "Eats sweets in class";

    public static final String MESSAGE_SUCCESS = "New detail added to %s: %s";

    private static Logger logger = Logger.getLogger("Add Detail Log");

    private final Index index;
    private final Detail detailToAdd;

    /**
     * Creates an AddAdditionalDetailCommand to add the specified {@code AdditionalDetail} to the student
     * at the specified {@code Index}.
     */
    public AddDetailCommand(Index index, Detail detailToAdd) {
        requireAllNonNull(index, detailToAdd);
        this.index = index;
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
        assert(index != null && detailToAdd != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getSortedStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(index.getZeroBased());

        List<Detail> details = studentToAddDetail.getDetails();
        details.add(detailToAdd);

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

        if (!(obj instanceof AddDetailCommand)) {
            return false;
        }

        AddDetailCommand other = (AddDetailCommand) obj;
        return index.equals(other.index) && detailToAdd.equals(other.detailToAdd);
    }
}
