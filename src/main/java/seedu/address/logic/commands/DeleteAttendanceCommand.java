package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;

public class DeleteAttendanceCommand extends AttendanceCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = AttendanceCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": deletes an Attendance from the student identified "
            + "by the date of the lesson. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE_DATE + "LESSON_DATE\n"
            + "Example: " + AttendanceCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_ATTENDANCE_DATE + "14/02/2020";
    public static final String MESSAGE_SUCCESS = "Attendance deleted for %s for the date of %s";
    public static final String MESSAGE_INVALID_ATTENDANCE_DATE = "There is no existing attendance for the entered date";

    private static Logger logger = Logger.getLogger("Delete Attendance Log");

    private final Index index;
    private final LocalDate attendanceDate;

    /**
     * Creates an EditAdditionalDetailCommand to add the specified {@code AdditionalDetail} to the student
     * at the specified {@code Index}.
     */
    public DeleteAttendanceCommand(Index index, LocalDate attendanceDate) {
        requireAllNonNull(index, attendanceDate);
        this.index = index;
        this.attendanceDate = attendanceDate;
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
        Student studentToDeleteAttendance = lastShownList.get(index.getZeroBased());

        List<Attendance> attendanceList = new ArrayList<>(studentToDeleteAttendance.getAttendance());
        List<Attendance> updatedAttendanceList = this.updateAttendanceList(attendanceList);

        Student updatedStudent = super.updateStudentAttendance(studentToDeleteAttendance, updatedAttendanceList);

        model.setStudent(studentToDeleteAttendance, updatedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), getUserInputDateString()));
    }

    private List<Attendance> updateAttendanceList(List<Attendance> attendanceList) throws CommandException {
        boolean containsAttendanceAtDate = attendanceList
                .stream()
                .anyMatch(attendance -> attendance.getLessonDate().equals(attendanceDate));

        if (!containsAttendanceAtDate) {
            throw new CommandException(MESSAGE_INVALID_ATTENDANCE_DATE);
        }

        Stream<Attendance> matchingAttendance = attendanceList.stream()
                .filter(attendance -> attendance.getLessonDate().equals(attendanceDate));
        Attendance attendanceToDelete = matchingAttendance.findFirst().get();
        attendanceList.remove(attendanceToDelete);

        return attendanceList;
    }

    private String getUserInputDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return attendanceDate.format(formatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeleteAttendanceCommand)) {
            return false;
        }

        DeleteAttendanceCommand other = (DeleteAttendanceCommand) obj;
        return index.equals(other.index) && attendanceDate.equals(other.attendanceDate);
    }
}
