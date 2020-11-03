package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.SchoolContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.YearMatchPredicate;
import seedu.address.testutil.FindStudentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNotebook());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand.FindStudentDescriptor firstDescriptor =
                new FindStudentDescriptorBuilder().withNamePredicate(firstPredicate).build();
        FindCommand.FindStudentDescriptor secondDescriptor =
                new FindStudentDescriptorBuilder().withNamePredicate(secondPredicate).build();

        FindCommand findFirstCommand = new FindCommand(firstDescriptor);
        FindCommand findSecondCommand = new FindCommand(secondDescriptor);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstDescriptor);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        expectedModel.updateFilteredStudentList(predicate);

        FindCommand command = new FindCommand(new FindStudentDescriptorBuilder()
                .withNamePredicate(predicate).build());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedStudentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        expectedModel.updateFilteredStudentList(predicate);

        FindCommand command = new FindCommand(new FindStudentDescriptorBuilder()
                .withNamePredicate(predicate).build());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getSortedStudentList());
    }

    @Test
    public void execute_multiplePredicates_oneStudentsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        SchoolContainsKeywordsPredicate schoolPredicate = prepareSchoolPredicate("Girls School");
        YearMatchPredicate yearMatchPredicate = prepareYearPredicate("Sec 2");
        List<Predicate<Student>> predicates = Arrays.asList(namePredicate,
                schoolPredicate, yearMatchPredicate);
        Predicate<Student> consolidatedPredicates = consolidatePredicates(predicates);
        expectedModel.updateFilteredStudentList(consolidatedPredicates);

        FindCommand.FindStudentDescriptor descriptor = new FindStudentDescriptorBuilder()
                .withNamePredicate(namePredicate).withSchoolPredicate(schoolPredicate)
                .withYearPredicate(yearMatchPredicate).build();
        FindCommand command = new FindCommand(descriptor);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getSortedStudentList());
    }

    @Test
    public void execute_multiplePredicates_noStudentFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        SchoolContainsKeywordsPredicate schoolPredicate = prepareSchoolPredicate("Girls School");
        YearMatchPredicate yearMatchPredicate = prepareYearPredicate("Sec 3");
        List<Predicate<Student>> predicates = Arrays.asList(namePredicate,
                schoolPredicate, yearMatchPredicate);
        Predicate<Student> consolidatedPredicates = consolidatePredicates(predicates);
        expectedModel.updateFilteredStudentList(consolidatedPredicates);

        FindCommand.FindStudentDescriptor descriptor = new FindStudentDescriptorBuilder()
                .withNamePredicate(namePredicate).withSchoolPredicate(schoolPredicate)
                .withYearPredicate(yearMatchPredicate).build();
        FindCommand command = new FindCommand(descriptor);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getSortedStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code SchoolContainsKeywordsPredicate}.
     */
    private SchoolContainsKeywordsPredicate prepareSchoolPredicate(String userInput) {
        return new SchoolContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code YearMatchPredicate}.
     */
    private YearMatchPredicate prepareYearPredicate(String year) throws ParseException {
        return new YearMatchPredicate(new Year(year));
    }

    /**
     * Parses {@code List<Predicate<Student>>} into a single {@code Predicate<Student>}.
     */
    private Predicate<Student> consolidatePredicates(List<Predicate<Student>> predicates) {
        Predicate<Student> identity = student -> true; // identity: returns the same predicate when composed with it
        return predicates.stream().reduce(identity, Predicate::and); // consolidates all predicates
    }
}
