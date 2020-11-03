package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to Reeve.\n\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_SCHOOL + "SCHOOL "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_VENUE + "CLASS_VENUE "
            + PREFIX_TIME + "CLASS_TIME "
            + "[" + PREFIX_FEE + "FEE] "
            + "[" + PREFIX_PAYMENT + "LAST_PAYMENT_DATE] "
            + "[" + PREFIX_DETAILS + "ADDITIONAL_DETAILS]\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_SCHOOL + "Woodlands Secondary School "
            + PREFIX_YEAR + "Secondary 2 "
            + PREFIX_VENUE + "347 Woodlands Ave 3, Singapore 730347 "
            + PREFIX_TIME + "1 1200-1400 "
            + PREFIX_FEE + "30 "
            + PREFIX_PAYMENT + "24/09/2020 "
            + PREFIX_DETAILS + "Details here ";

    public static final String MESSAGE_SUCCESS = "New student added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in Reeve";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        if (model.isClashingClassTime(toAdd)) {
            throw new CommandException(Messages.MESSAGE_CLASHING_LESSON);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
