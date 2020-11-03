package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;

/**
 * Resolves a specified question of a student at the displayed index in Reeve.
 */
public class SolveQuestionCommand extends QuestionCommand {

    public static final String MESSAGE_SUCCESS = "%1$s's question resolved: %2$s";
    public static final String MESSAGE_SOLVED_QUESTION = "This question was already solved";
    public static final String MESSAGE_BAD_QUESTION_INDEX = "There is no question at this index";
    public static final String COMMAND_WORD = "solve";

    private static Logger logger = Logger.getLogger("Solve Question Log");

    private final Index studentIndex;
    private final Index questionIndex;
    private final String solution;

    /**
     * Constructs a SolveQuestionCommand to mark a specified {@code Question} as solved.
     */
    public SolveQuestionCommand(Index studentIndex, Index questionIndex, String solution) {
        requireAllNonNull(studentIndex, questionIndex, solution);
        this.studentIndex = studentIndex;
        this.questionIndex = questionIndex;
        this.solution = solution;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (studentIndex != null) && (questionIndex != null) && (solution != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getSortedStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Handling non-existent student error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student asker = lastShownList.get(studentIndex.getZeroBased());
        checkQuestionIndex(asker);

        Question target = asker.getQuestions().get(questionIndex.getZeroBased());
        Question solved = new SolvedQuestion(target.question, solution);
        Student replacement = asker.setQuestion(target, solved);

        model.setStudent(asker, replacement);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, replacement.getName(), solved));
    }

    /***
     * Checks if the question at the specified index exists or has already been solved.
     */
    private void checkQuestionIndex(Student asker) throws CommandException {
        if (questionIndex.getZeroBased() >= asker.getQuestions().size()) {
            logger.log(Level.WARNING, "Handling non-existent question error");
            throw new CommandException(MESSAGE_BAD_QUESTION_INDEX);
        }
        if (asker.getQuestions().get(questionIndex.getZeroBased()).isResolved()) {
            logger.log(Level.WARNING, "Handling solved question error");
            throw new CommandException(MESSAGE_SOLVED_QUESTION);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SolveQuestionCommand)) {
            return false;
        }

        SolveQuestionCommand other = (SolveQuestionCommand) obj;
        return studentIndex.equals(other.studentIndex)
                && questionIndex.equals(other.questionIndex)
                && solution.equals(other.solution);
    }

}
