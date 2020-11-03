package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;

public class AddAttendanceCommand extends AttendanceCommand {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = AttendanceCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": adds an Attendance to the student identified "
            + "by the index number used in the displayed student list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE_DATE + "LESSON_DATE " + PREFIX_ATTENDANCE_STATUS + "ATTENDANCE_STATUS "
            + PREFIX_ATTENDANCE_FEEDBACK + "FEEDBACK\n"
            + "Example: " + AttendanceCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_ATTENDANCE_DATE + "14/02/2020 " + PREFIX_ATTENDANCE_STATUS + "present "
            + PREFIX_ATTENDANCE_FEEDBACK + "attentive";
    public static final String MESSAGE_SUCCESS = "Attendance added for %s: %s";
    public static final String MESSAGE_INVALID_ATTENDANCE_DATE =
            "There is already an existing attendance for the entered date! Please use another date, or delete the "
            + "existing attendance before adding a new one.";
    private static Logger logger = Logger.getLogger("Add Attendance Log");

    private final Index index;
    private final Attendance attendanceToAdd;

    /**
     * Creates an AddAdditionalDetailCommand to add the specified {@code AdditionalDetail} to the student
     * at the specified {@code Index}.
     */
    public AddAttendanceCommand(Index index, Attendance attendanceToAdd) {
        requireAllNonNull(index, attendanceToAdd);
        this.index = index;
        this.attendanceToAdd = attendanceToAdd;
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
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getSortedStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToAddAttendance = lastShownList.get(index.getZeroBased());

        List<Attendance> attendanceList = new ArrayList<>(studentToAddAttendance.getAttendance());
        this.updateAttendanceList(attendanceList);

        Student updatedStudent = super.updateStudentAttendance(studentToAddAttendance, attendanceList);

        model.setStudent(studentToAddAttendance, updatedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), attendanceToAdd));
    }

    private List<Attendance> updateAttendanceList(List<Attendance> attendanceList) throws CommandException {
        boolean containsAttendanceAtDate = attendanceList
                .stream()
                .anyMatch(attendance -> attendance.getLessonDate().equals(attendanceToAdd.getLessonDate()));

        if (containsAttendanceAtDate) {
            throw new CommandException(MESSAGE_INVALID_ATTENDANCE_DATE);
        }
        attendanceList.add(attendanceToAdd);
        return attendanceList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AddAttendanceCommand)) {
            return false;
        }

        AddAttendanceCommand other = (AddAttendanceCommand) obj;
        return index.equals(other.index) && attendanceToAdd.equals(other.attendanceToAdd);
    }
}
