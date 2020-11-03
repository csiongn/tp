package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.ArrayList;

import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

/**
 * Abstract class of exam commands such as AddExam and DeleteExam.
 */
public abstract class ExamCommand extends Command {
    public static final String COMMAND_WORD = "exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/deletes an exam "
            + "or view exam statistics of student "
            + "to/from a student in Reeve.\n\n"
            + "Example:\n"
            + "To add an exam:\n"
            + ExamCommand.COMMAND_WORD
            + " add" + " 1 "
            + PREFIX_EXAM_NAME
            + " End of Year Examination 2020 "
            + PREFIX_EXAM_DATE + "7/11/2020 "
            + PREFIX_SCORE + "50/100 \n(adds an exam to the first student in the list)\n\n"
            + "To delete an exam:\n"
            + ExamCommand.COMMAND_WORD
            + " delete" + " 2 "
            + PREFIX_EXAM_INDEX + "1 \n(deletes the first exam of the second student in the list)\n\n"
            + "To view a student's exam statistics:\n"
            + ExamCommand.COMMAND_WORD
            + " stats" + " 1 \n(view statistics of first student in the list)";

    /**
     * Updates a specific students exam list when an exam is being added or deleted.
     * @param studentToUpdate selected student to be be updated.
     * @param exams the updated array list of exams.
     * @return selected student with the updated exams.
     */
    public Student updateStudentExam(Student studentToUpdate, ArrayList<Exam> exams) {
        return new Student(studentToUpdate.getName(), studentToUpdate.getPhone(),
                studentToUpdate.getSchool(), studentToUpdate.getYear(), studentToUpdate.getAdmin(),
                studentToUpdate.getQuestions(), exams, studentToUpdate.getAttendance());
    }
}
