package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    static final Attendance VALID_ATTENDANCE = new Attendance("14/04/1998", "present",
            new Feedback("sleepy"));
    static final String VALID_DATE_DEF = "13/03/2020";
    static final String VALID_DATE_ALT = "13/3/20";
    static final String INVALID_DATE = "2020-02-12";

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All fields null
        assertThrows(NullPointerException.class, () -> new Attendance(null, null,
                new Feedback(null)));

        // All but one null
        assertThrows(NullPointerException.class, () -> new Attendance("14/04/1998", null,
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, "present",
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, null,
                new Feedback("sleepy")));

        // Only one null
        assertThrows(NullPointerException.class, () -> new Attendance("14/04/1998", "present",
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, "present",
                new Feedback("sleepy")));
        assertThrows(NullPointerException.class, () -> new Attendance("14/04/1998", null,
                new Feedback("sleepy")));
    }

    @Test
    public void constructor_invalidAttendanceFields_throwsIllegalArgumentException() {
        String invalidAttendanceDate = "";
        String invalidAttendanceStatus = "hey";
        String invalidAttendanceFeedback = "";

        // all 3 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendanceDate,
                invalidAttendanceStatus, new Feedback(invalidAttendanceFeedback)));

        // 2 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance("12/02/2020",
                invalidAttendanceStatus, new Feedback(invalidAttendanceFeedback)));
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendanceDate,
                "present", new Feedback(invalidAttendanceFeedback)));
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendanceDate,
                invalidAttendanceStatus, new Feedback("attentive")));

        // 1 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance("12/02/2020",
                "present", new Feedback(invalidAttendanceFeedback)));
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendanceDate,
                "present", new Feedback("sleepy")));
        assertThrows(IllegalArgumentException.class, () -> new Attendance("12/02/2020",
                invalidAttendanceStatus, new Feedback("attentive")));
    }

    @Test
    public void isValidDate_test() {
        // null Date should throw exception
        assertThrows(NullPointerException.class, () -> Attendance.isValidDate(null));
        // emptyDate is invalid
        assertFalse(Attendance.isValidDate(" "));
        // Invalid format
        assertFalse(Attendance.isValidDate(INVALID_DATE));

        // valid format date
        assertTrue(Attendance.isValidDate(VALID_DATE_DEF));
        assertTrue(Attendance.isValidDate(VALID_DATE_ALT));
    }

    @Test
    public void equals_test() {

        // different object
        assertNotEquals(VALID_ATTENDANCE, "hey");

        // diff fields
        assertNotEquals(VALID_ATTENDANCE, new Attendance("17/04/1998", "present",
                new Feedback("sleepy")));
        assertNotEquals(VALID_ATTENDANCE, new Attendance("14/04/1998", "absent",
                new Feedback("sleepy")));
        assertNotEquals(VALID_ATTENDANCE, new Attendance("14/04/1998", "present",
                new Feedback("sleepayy son")));

        // same object
        assertEquals(VALID_ATTENDANCE, VALID_ATTENDANCE);

        // same fields
        assertEquals(VALID_ATTENDANCE, new Attendance("14/04/1998", "present",
                new Feedback("sleepy")));

    }

}
