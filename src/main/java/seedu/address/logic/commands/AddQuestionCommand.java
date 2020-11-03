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
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Adds an unresolved question to a student at the displayed index in Reeve.
 */
public class AddQuestionCommand extends QuestionCommand {

    public static final String MESSAGE_SUCCESS = "New question added to student %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_QUESTION = "This student has already asked this question";
    public static final String COMMAND_WORD = "add";

    private static Logger logger = Logger.getLogger("Add Question Log");

    private final Index index;
    private final UnsolvedQuestion questionToAdd;

    /**
     * Creates an AddQuestionCommand to add the specified {@code Question} to the student
     * at the specified {@code Index}.
     */
    public AddQuestionCommand(Index index, UnsolvedQuestion questionToAdd) {
        requireAllNonNull(index, questionToAdd);

        this.index = index;
        this.questionToAdd = questionToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert (index != null) && (questionToAdd != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getSortedStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Handling non-existent student error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student asker = lastShownList.get(index.getZeroBased());
        if (asker.containsQuestion(questionToAdd)) {
            logger.log(Level.WARNING, "Handling duplicate question error");
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        Student replacement = asker.addQuestion(questionToAdd);
        model.setStudent(asker, replacement);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, replacement.getName(), questionToAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AddQuestionCommand)) {
            return false;
        }

        AddQuestionCommand other = (AddQuestionCommand) obj;
        return index.equals(other.index) && questionToAdd.equals(other.questionToAdd);
    }

}
