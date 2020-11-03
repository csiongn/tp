package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

/**
 * Adds an exam to a student.
 */
public class AddExamCommand extends ExamCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = ExamCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Adds an exam to a student.\n\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EXAM_NAME + "EXAM_NAME "
            + PREFIX_EXAM_DATE + "EXAM_DATE "
            + PREFIX_SCORE + "SCORE \n\n"
            + "Example: "
            + ExamCommand.COMMAND_WORD + " "
            + COMMAND_WORD + " 1 "
            + PREFIX_EXAM_NAME
            + " End of Year Examination 2020 "
            + PREFIX_EXAM_DATE + "7/11/2020 "
            + PREFIX_SCORE + "50/100 ";

    public static final String MESSAGE_EXAM_ADDED_SUCCESS = "New exam added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_EXAM = "%1$s already exists under %2$s";
    public static final String MESSAGE_EXAM_INVALID_NAME = "Exam names should be should only contain "
            + "alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_EXAM_INVALID_DATE = "Exam dates should be in the form dd/mm/yy, "
            + "and should not be blank";

    private final Index index;
    private final Exam toAdd;


    /**
     * Creates an AddExamCommand to add the specified {@code Exam} to a specified student based on index.
     */
    public AddExamCommand(Index index, Exam exam) {
        requireAllNonNull(index, exam);
        this.index = index;
        toAdd = exam;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getSortedStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student selectedStudent = lastShownList.get(index.getZeroBased());

        if (selectedStudent.getExams().contains(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXAM,
                    toAdd.getName(), selectedStudent.getName()));
        }

        ArrayList<Exam> exams = new ArrayList<>(selectedStudent.getExams());
        exams.add(toAdd);
        Student updatedStudent = new Student(selectedStudent.getName(), selectedStudent.getPhone(),
                selectedStudent.getSchool(), selectedStudent.getYear(), selectedStudent.getAdmin(),
                selectedStudent.getQuestions(), exams, selectedStudent.getAttendance());
        model.setStudent(selectedStudent, updatedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EXAM_ADDED_SUCCESS, updatedStudent.getName(), toAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AddExamCommand)) {
            return false;
        }

        AddExamCommand other = (AddExamCommand) obj;
        return index.equals(other.index) && toAdd.equals(other.toAdd);
    }
}
