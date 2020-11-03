package seedu.address.model.student.academic;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Attendance {

    public static final String DATE_CONSTRAINTS =
            "Attendance dates should be valid and in the form dd/mm/yy, and should not be blank";
    public static final String STATUS_CONSTRAINTS =
            "Attendance status should be either 'present' or 'absent'.";

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private static final String PRESENT_STATUS = "present";
    private static final String ABSENT_STATUS = "absent";

    private LocalDate lessonDate;
    private boolean isPresent;
    private Feedback feedback;

    /**
     * Constructs a {@code Attendance} object.
     * @param date date of lesson
     * @param isPresent whether student was present for lesson
     * @param feedback feedback for lesson
     */
    public Attendance(String date, String isPresent, Feedback feedback) {
        requireAllNonNull(date, isPresent, feedback);
        checkArgument(isValidDate(date), DATE_CONSTRAINTS);
        checkArgument(isValidAttendanceStatus(isPresent), STATUS_CONSTRAINTS);

        this.lessonDate = parseDate(date);
        this.isPresent = parseAttendanceStatus(isPresent);
        this.feedback = feedback;
    }

    /**
     * Parses userinput into a proper date.
     * @param input user input date String
     * @return date understood by Reeve
     */
    public static LocalDate parseDate(String input) {
        LocalDate date;
        try {
            date = LocalDate.parse(input, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            date = LocalDate.parse(input, INPUT_ALT);
        }
        return date;
    }

    /**
     * Returns true if a given string is a valid date for {@code Attendance}.
     */
    public static boolean isValidDate(String date) {
        String validationRegex = "(\\d{1,2})(\\/)(\\d{1,2})(\\/)(\\d{2}|\\d{4})";
        if (!date.matches(validationRegex)) {
            return false;
        }

        LocalDate testDate = null;

        for (DateTimeFormatter format : new DateTimeFormatter[] {INPUT_DEF, INPUT_ALT}) {
            try {
                testDate = LocalDate.parse(date, format);
                break;
            } catch (DateTimeParseException ignored) {
                // does not match the DateTimeFormat, try the next
            }
        }
        return testDate != null;
    }

    public static boolean isValidAttendanceStatus(String status) {
        return status.equals(PRESENT_STATUS) || status.equals(ABSENT_STATUS);
    }

    public static boolean parseAttendanceStatus(String status) {
        return status.equals(PRESENT_STATUS); // if false, then will be equal to ABSENT_STATUS
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public String getUserInputDate() {
        return lessonDate.format(INPUT_ALT);
    }

    public String getOutputDate() {
        return lessonDate.format(OUTPUT);
    }

    public boolean getAttendanceStatus() {
        return isPresent;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %s",
                getOutputDate(), (getAttendanceStatus() ? "\u2713" : "\u2718"), getFeedback());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Attendance)) {
            return false;
        }

        Attendance other = (Attendance) obj;
        return other.getLessonDate().equals(getLessonDate())
                && other.getAttendanceStatus() == getAttendanceStatus()
                && other.getFeedback().equals(getFeedback());
    }
}
