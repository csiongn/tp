package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.notes.EditNoteCommand;
import seedu.address.model.Model;
import seedu.address.model.Reeve;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.notes.EditNoteDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_SCHOOL_AMY = "NUS High School";
    public static final String VALID_SCHOOL_BOB = "Hwa Chong Institution";
    public static final String VALID_YEAR_AMY = "Secondary 2";
    public static final String VALID_YEAR_BOB = "Secondary 4";
    public static final String VALID_CLASS_TIME_AMY = "2 1500-1600";
    public static final String VALID_CLASS_TIME_BOB = "5 0900-1100";
    public static final String VALID_CLASS_VENUE_AMY = "347 Woodlands Ave 3, Singapore 730347";
    public static final String VALID_CLASS_VENUE_BOB = "347 Woodlands Ave 3, Singapore 730347";
    public static final String VALID_FEE_AMY = "40";
    public static final String VALID_FEE_BOB = "55";
    public static final String VALID_PAYMENT_DATE_AMY = "27/10/2020";
    public static final String VALID_PAYMENT_DATE_BOB = "10/09/2020";
    public static final String VALID_ADDITIONAL_DETAILS_AMY = "Clever";
    public static final String VALID_ADDITIONAL_DETAILS_BOB = "Hardworking";
    public static final String VALID_QUESTION_AMY = "How is current calculated from resistance?";
    public static final String VALID_QUESTION_BOB = "What is hemoglobin?";
    public static final String VALID_EXAM_NAME_AMY = "Mid Year 2020";
    public static final String VALID_EXAM_NAME_BOB = "End of Year 2020";
    public static final String VALID_EXAM_DATE_AMY = "26/7/2020";
    public static final String VALID_EXAM_DATE_BOB = "5/11/2020";
    public static final String VALID_EXAM_SCORE_AMY = "26/50";
    public static final String VALID_EXAM_SCORE_BOB = "50/50";
    public static final String VALID_ATTENDANCE_DATE_AMY = "27/10/2020";
    public static final String VALID_ATTENDANCE_DATE_BOB = "19/4/2009";
    public static final String VALID_ATTENDANCE_STATUS_AMY = "present";
    public static final String VALID_ATTENDANCE_STATUS_BOB = "absent";
    public static final String VALID_ATTENDANCE_FEEDBACK_AMY = "attentive";
    public static final String VALID_ATTENDANCE_FEEDBACK_BOB = "sick";
    public static final String VALID_TITLE = "W10 blues";
    public static final String VALID_DESCRIPTION = "why must i do cs2103";
    public static final String VALID_TITLE_GRADUATION = "Graduation :(";
    public static final String VALID_DESCRIPTION_GRADUATION = "write cards for people";
    public static final String VALID_TITLE_RANDOM = "HEHEHEHE";
    public static final String VALID_DESCRIPTION_RANDOM = ":)";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String SCHOOL_DESC_AMY = " " + PREFIX_SCHOOL + VALID_SCHOOL_AMY;
    public static final String SCHOOL_DESC_BOB = " " + PREFIX_SCHOOL + VALID_SCHOOL_BOB;
    public static final String YEAR_DESC_AMY = " " + PREFIX_YEAR + VALID_YEAR_AMY;
    public static final String YEAR_DESC_BOB = " " + PREFIX_YEAR + VALID_YEAR_BOB;

    public static final String CLASS_VENUE_DESC_AMY = " " + PREFIX_VENUE + VALID_CLASS_VENUE_AMY;
    public static final String CLASS_VENUE_DESC_BOB = " " + PREFIX_VENUE + VALID_CLASS_VENUE_BOB;
    public static final String CLASS_TIME_DESC_AMY = " " + PREFIX_TIME + VALID_CLASS_TIME_AMY;
    public static final String CLASS_TIME_DESC_BOB = " " + PREFIX_TIME + VALID_CLASS_TIME_BOB;
    public static final String FEE_DESC_AMY = " " + PREFIX_FEE + VALID_FEE_AMY;
    public static final String FEE_DESC_BOB = " " + PREFIX_FEE + VALID_FEE_BOB;
    public static final String PAYMENT_DATE_DESC_AMY = " " + PREFIX_PAYMENT + VALID_PAYMENT_DATE_AMY;
    public static final String PAYMENT_DATE_DESC_BOB = " " + PREFIX_PAYMENT + VALID_PAYMENT_DATE_BOB;
    public static final String ADDITIONAL_DETAILS_DESC_AMY = " " + PREFIX_DETAILS + VALID_ADDITIONAL_DETAILS_AMY;
    public static final String ADDITIONAL_DETAILS_DESC_BOB = " " + PREFIX_DETAILS + VALID_ADDITIONAL_DETAILS_BOB;

    public static final String QUESTION_DESC_AMY = " " + PREFIX_TEXT + VALID_QUESTION_AMY;
    public static final String ADDITIONAL_DETAIL_DESC_AMY = " " + PREFIX_TEXT + VALID_ADDITIONAL_DETAILS_AMY;

    public static final String TITLE_DESC_EXISTENTIAL_CRISIS = " " + PREFIX_TITLE + VALID_TITLE;
    public static final String DESCRIPTION_DESC_EXISTENTIAL_CRISIS = " "
            + PREFIX_DESCRIPTION + VALID_DESCRIPTION;
    public static final String TITLE_DESC_GRADUATION = " " + PREFIX_TITLE + VALID_TITLE_GRADUATION;
    public static final String DESCRIPTION_DESC_GRADUATION = " "
            + PREFIX_DESCRIPTION + VALID_DESCRIPTION_GRADUATION;

    public static final String QUESTION_MATH = "What is 1 + 1?";
    public static final String QUESTION_PHYSICS = "How does Newton's Second Law work?";
    public static final String QUESTION_EMO = "What is the point of life?";
    public static final String[] TEST_QUESTIONS =
            new String[] {QUESTION_MATH, QUESTION_PHYSICS, QUESTION_EMO};

    public static final String EXAM_DESC_AMY = " " + PREFIX_EXAM_NAME + VALID_EXAM_NAME_AMY + " "
            + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY + " "
            + PREFIX_SCORE + VALID_EXAM_SCORE_AMY;
    public static final String EXAM_DESC_BOB = " " + PREFIX_EXAM_NAME + VALID_EXAM_NAME_BOB + " "
            + PREFIX_EXAM_DATE + VALID_EXAM_DATE_BOB + " "
            + PREFIX_SCORE + VALID_EXAM_SCORE_BOB;

    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE_DATE + VALID_ATTENDANCE_DATE_AMY + " "
            + PREFIX_ATTENDANCE_STATUS + VALID_ATTENDANCE_STATUS_AMY + " "
            + PREFIX_ATTENDANCE_FEEDBACK + VALID_ATTENDANCE_FEEDBACK_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_ATTENDANCE_DATE + VALID_ATTENDANCE_DATE_BOB + " "
            + PREFIX_ATTENDANCE_STATUS + VALID_ATTENDANCE_STATUS_BOB + " "
            + PREFIX_ATTENDANCE_FEEDBACK + VALID_ATTENDANCE_FEEDBACK_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_SCHOOL_DESC = " " + PREFIX_SCHOOL; // empty string not allowed for schools
    public static final String INVALID_YEAR_DESC = " " + PREFIX_YEAR + "$4"; // '$' not allowed in year
    public static final String INVALID_VENUE_DESC = " " + PREFIX_VENUE + ""; // only empty venues are not allowed
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "1 @100"; // '@' not allowed in times
    public static final String INVALID_FEE_DESC = " " + PREFIX_FEE + "$20"; // '$' allowed for schools
    // 'alphabets' not allowed in payment date
    public static final String INVALID_PAYMENT_DESC = " " + PREFIX_PAYMENT + "alphabets";
    // '*' not allowed in details
    public static final String INVALID_ADDITIONAL_DETAIL_DESC = " " + PREFIX_DETAILS + "hubby*";
    // empty string not allowed for exam names
    public static final String INVALID_EXAM_NAME_DESC = " " + PREFIX_EXAM_NAME + " " + " "
            + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY + " "
            + PREFIX_SCORE + VALID_EXAM_SCORE_AMY;
    // 'alphabets' not allowed in exam date
    public static final String INVALID_EXAM_DATE_DESC = " " + PREFIX_EXAM_NAME + VALID_EXAM_NAME_AMY + " "
            + PREFIX_EXAM_DATE + "alphabets" + " "
            + PREFIX_SCORE + VALID_EXAM_SCORE_AMY;
    // first int of scores should be <= to second int
    public static final String INVALID_EXAM_SCORE_DESC_MORE = " " + PREFIX_EXAM_NAME + VALID_EXAM_NAME_AMY + " "
            + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY + " "
            + PREFIX_SCORE + "100/50";
    // both ints of scores cannot not be negative
    public static final String INVALID_EXAM_SCORE_DESC_NEGATIVE = " " + PREFIX_EXAM_NAME + VALID_EXAM_NAME_AMY + " "
            + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY + " "
            + PREFIX_SCORE + "-50/-100";

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "BABABANBNANABBABABSNBA";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dapibus sapienar";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;
    public static final EditNoteCommand.EditNoteDescriptor DESC_NOTE_TODO;
    public static final EditNoteCommand.EditNoteDescriptor DESC_NOTE_EVENTS;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        DESC_NOTE_TODO = new EditNoteDescriptorBuilder().withTitle("todo")
                .withDescription("watch youtube").build();
        DESC_NOTE_EVENTS = new EditNoteDescriptorBuilder().withTitle("events")
                .withDescription("attend practical exam").build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Reeve expectedReeve = new Reeve(actualModel.getReeve());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getSortedStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedReeve, actualModel.getReeve());
        assertEquals(expectedFilteredList, actualModel.getSortedStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedStudentList().size());

        Student student = model.getSortedStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getSortedStudentList().size());
    }

}
