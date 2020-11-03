package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class ExamStatsCommand extends ExamCommand {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = ExamCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Shows exam stats of specified student.\n\n"
            + "Parameters: INDEX (must be a positive integer) \n\n"
            + "Example: "
            + ExamCommand.COMMAND_WORD
            + " " + COMMAND_WORD + " 2 ";

    public static final String MESSAGE_EXAM_STATS_SUCCESS = "Opened exam statistics";

    private final Index studentIndex;


    /**
     * Creates an AddExamCommand to delete the specified {@code Exam} of a specified student based on index.
     */
    public ExamStatsCommand(Index studentIndex) {
        requireAllNonNull(studentIndex);
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getSortedStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToShow = lastShownList.get(studentIndex.getZeroBased());
        return new CommandResult(MESSAGE_EXAM_STATS_SUCCESS, studentToShow);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ExamStatsCommand)) {
            return false;
        }

        ExamStatsCommand other = (ExamStatsCommand) obj;
        return studentIndex.equals(other.studentIndex);
    }
}
