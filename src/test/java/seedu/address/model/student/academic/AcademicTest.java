package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class AcademicTest {

    @Test
    public void equals() {
        Academic aliceAcademic = ALICE.getAcademic();

        // same values should return true
        Academic academicCopy = new StudentBuilder(ALICE).build().getAcademic();
        assertTrue(aliceAcademic.equals(academicCopy));

        // same object return true
        assertTrue(aliceAcademic.equals(aliceAcademic));

        // null returns false
        assertFalse(aliceAcademic.equals(null));

        // different types should return false
        assertFalse(aliceAcademic.equals(6));

        // different attendance list returns false
        Academic editedAliceAcademic = new StudentBuilder(ALICE).withAttendances(new Attendance("12/02/2020",
                "present", new Feedback("sleeping"))).build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));
    }
}
