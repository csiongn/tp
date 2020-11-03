package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class NameComparatorTest {

    private NameComparator cmp = new NameComparator();

    private Student eve = new StudentBuilder().withName("Eve").withSchool("Bishan Primary School")
            .withYear("P3").build();
    private Student alice = new StudentBuilder().withName("Alice").withSchool("SST")
            .withYear("Sec 1").build();
    private Student aloysius = new StudentBuilder().withName("aloysius").withSchool("SST")
            .withYear("Sec 1").build();
    private Student dominic = new StudentBuilder().withName("dominic").withSchool("SOTA")
            .withYear("Sec 1").build();
    private Student pikachu = new StudentBuilder().withName("pikachu").withSchool("Trainers School")
            .withYear("Sec 3").build();
    private Student bob = new StudentBuilder().withName("Bob").withSchool("Innova JC")
            .withYear("JC 1").build();

    @Test
    public void test_smallerThan_lesThanZero() {
        assertTrue(cmp.compare(alice, dominic) < 0);
        assertTrue(cmp.compare(alice, pikachu) < 0);
        assertTrue(cmp.compare(alice, bob) < 0);
        assertTrue(cmp.compare(alice, aloysius) < 0);
        assertTrue(cmp.compare(aloysius, eve) < 0);
        assertTrue(cmp.compare(bob, pikachu) < 0);
        assertTrue(cmp.compare(eve, pikachu) < 0);
    }

    @Test
    public void test_sameStudent_returnsZero() {
        assertEquals(0, cmp.compare(eve, eve));
    }

    @Test
    public void test_largerThan_greaterThanZero() {
        assertTrue(cmp.compare(dominic, aloysius) > 0);
        assertTrue(cmp.compare(bob, aloysius) > 0);
        assertTrue(cmp.compare(eve, bob) > 0);
    }
}
