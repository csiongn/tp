package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;
import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // different phone and school and year -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withSchool(VALID_SCHOOL_BOB)
                .withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, different attributes -> returns false
        editedAlice = new StudentBuilder(ALICE).withSchool(VALID_SCHOOL_BOB)
                .withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same school, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same year, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withSchool(VALID_SCHOOL_BOB)
                .build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, same school, different year -> returns true
        editedAlice = new StudentBuilder(ALICE).withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different school -> returns false
        editedAlice = new StudentBuilder(ALICE).withSchool(VALID_SCHOOL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different year -> returns false
        editedAlice = new StudentBuilder(ALICE).withYear(VALID_YEAR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void containsQuestion() {
        String test1 = "What is 1+1?";
        String test2 = "How do you do grammar?";
        String test3 = "Do giraffes fart?";
        String[] questions = new String[] {test1, test2};
        Student editedAlice = new StudentBuilder(ALICE).withQuestions(questions).build();

        assertTrue(editedAlice.containsQuestion(new UnsolvedQuestion(test1)));
        assertTrue(editedAlice.containsQuestion(new UnsolvedQuestion(test2)));
        assertTrue(editedAlice.containsQuestion(new SolvedQuestion(test1, DEFAULT_SOLUTION)));
        assertTrue(editedAlice.containsQuestion(new SolvedQuestion(test2, DEFAULT_SOLUTION)));

        assertFalse(editedAlice.containsQuestion(new UnsolvedQuestion(test3)));
    }

    @Test
    public void addQuestion() {
        Student editedAlice = new StudentBuilder(ALICE).withQuestions().build();
        Student newAlice = editedAlice.addQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Student expected = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build();
        assertNotEquals(editedAlice, newAlice);
        assertEquals(expected, newAlice);
    }

    @Test
    public void setQuestion() {
        Student editedAlice = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build();
        Student newAlice = editedAlice.setQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH),
                new SolvedQuestion(DEFAULT_QUESTION_MATH, DEFAULT_SOLUTION));
        Student expected = new StudentBuilder(ALICE).withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_MATH).build();
        assertNotEquals(editedAlice, newAlice);
        assertEquals(expected, newAlice);
    }

    @Test
    public void deleteQuestion() {
        Student editedAlice = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build();
        Student newAlice = editedAlice.deleteQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Student expected = new StudentBuilder(ALICE).withQuestions().build();
        assertNotEquals(editedAlice, newAlice);
        assertEquals(expected, newAlice);

        editedAlice = new StudentBuilder(ALICE).withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_MATH).build();
        newAlice = editedAlice.deleteQuestion(new SolvedQuestion(DEFAULT_QUESTION_MATH, DEFAULT_SOLUTION));
        assertNotEquals(editedAlice, newAlice);
        assertEquals(expected, newAlice);
    }

}
