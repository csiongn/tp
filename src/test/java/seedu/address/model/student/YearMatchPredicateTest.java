package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class YearMatchPredicateTest {

    private Year secondaryOne = new Year("Sec 1");
    private Year secondaryOneDuplicate = new Year("Sec 1");
    private Year secondaryThree = new Year("Sec 3");

    private YearMatchPredicate yearMatchPredicateSecondaryOne = new YearMatchPredicate(secondaryOne);
    private YearMatchPredicate yearMatchPredicateSecondaryThree = new YearMatchPredicate(secondaryThree);

    private YearMatchPredicate yearMatchPredicateSecondaryOneDuplicate =
            new YearMatchPredicate(secondaryOneDuplicate);

    private Student yearOneStudent = new StudentBuilder().withName("Alice").withSchool("Changi Junior College")
            .withYear("Sec 1").build();
    private Student yearThreeStudent = new StudentBuilder().withName("Pikachu").withSchool("Trainers School")
            .withYear("Sec 3").build();

    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(yearMatchPredicateSecondaryOne, yearMatchPredicateSecondaryOne);

        // different type -> return false
        assertNotEquals("1", yearMatchPredicateSecondaryOne);

        // null -> returns false
        assertNotEquals(yearMatchPredicateSecondaryOne, null);

        // same year -> returns true
        assertTrue(yearMatchPredicateSecondaryOne.equals(yearMatchPredicateSecondaryOneDuplicate));

        // different year -> returns false
        assertFalse(yearMatchPredicateSecondaryOne.equals(yearMatchPredicateSecondaryThree));

    }

    @Test
    public void test_matchingYear_returnsTrue() {
        assertTrue(yearMatchPredicateSecondaryOne.test(yearOneStudent));
        assertTrue(yearMatchPredicateSecondaryThree.test(yearThreeStudent));

        // User guide test cases
        assertTrue(yearMatchPredicateSecondaryThree.test(new StudentBuilder()
                .withYear("Sec 3").build()));

    }

    @Test
    public void test_notMatchingYear_returnsFalse() {
        assertFalse(yearMatchPredicateSecondaryOne.test(yearThreeStudent));

        // User guide test cases
        assertFalse(yearMatchPredicateSecondaryThree.test(new StudentBuilder()
                .withYear("Sec 4").build()));
    }
}
