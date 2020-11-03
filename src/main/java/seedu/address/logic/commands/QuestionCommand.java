package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve. "
            + "Supported actions: \n"
            + "add INDEX " + PREFIX_TEXT + "QUESTION: adds an unresolved question.\n"
            + "Example: " + COMMAND_WORD + " add 1 " + PREFIX_TEXT + "How do birds fly\n"
            + "solve INDEX " + PREFIX_INDEX + "QUESTION_INDEX "
            + PREFIX_TEXT + "SOLUTION: marks a question as solved.\n"
            + "Example: " + COMMAND_WORD + " solve 1 " + PREFIX_INDEX + " 1 "
            + PREFIX_TEXT + "Read your textbook.\n"
            + "delete INDEX " + PREFIX_INDEX + "QUESTION_INDEX: deletes a question.\n"
            + "Example: " + COMMAND_WORD + " delete 2 " + PREFIX_INDEX + " 1 \n";
}
