package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class YearComparatorTest {

    private YearComparator cmp = new YearComparator();

    private Student priThreeStudent = new StudentBuilder().withName("Eve").withSchool("Bishan Primary School")
            .withYear("Primary 3").build();
    private Student secOneStudent = new StudentBuilder().withName("Alice").withSchool("SST")
            .withYear("Secondary 1").build();
    private Student secOneStudent2 = new StudentBuilder().withName("Dominic").withSchool("SOTA")
            .withYear("Secondary 1").build();
    private Student secThreeStudent = new StudentBuilder().withName("Pikachu").withSchool("Trainers School")
            .withYear("Secondary 3").build();
    private Student jcOneStudent = new StudentBuilder().withName("Bob").withSchool("Innova JC")
            .withYear("JC 1").build();

    @Test
    public void test_largerThan_moreThanZero() {
        assertTrue(cmp.compare(jcOneStudent, secThreeStudent) > 0);
        assertTrue(cmp.compare(jcOneStudent, secOneStudent) > 0);
        assertTrue(cmp.compare(jcOneStudent, priThreeStudent) > 0);
        assertTrue(cmp.compare(secThreeStudent, secOneStudent) > 0);
    }

    @Test
    public void test_sameYear_returnsZero() {
        assertEquals(0, cmp.compare(secOneStudent, secOneStudent2));
        assertEquals(0, cmp.compare(secOneStudent, secOneStudent));
    }

    @Test
    public void test_smallerThan_lessThanZero() {
        assertTrue(cmp.compare(priThreeStudent, secThreeStudent) < 0);
        assertTrue(cmp.compare(priThreeStudent, secOneStudent) < 0);
    }
}
