package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class JsonAdaptedAttendance {

    public static final String MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT = "Attendance's %s field is missing!";

    private final String date;
    private final String attendanceStatus;
    private final String feedback;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code exam}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("date") String date,
                                 @JsonProperty("attendanceStatus") String attendanceStatus,
                                 @JsonProperty("feedback") String feedback) {
        this.date = date;
        this.attendanceStatus = attendanceStatus;
        this.feedback = feedback;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        this.date = source.getUserInputDate();
        this.attendanceStatus = source.getAttendanceStatus() ? "present" : "absent";
        this.feedback = source.getFeedback().toString();
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (date == null || date.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT,
                    "Attendance date"));
        }

        if (attendanceStatus == null || attendanceStatus.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT,
                    "Attendance status"));
        }

        if (feedback == null || feedback.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_ATTENDANCE_FIELD_MESSAGE_FORMAT,
                    "Feedback"));
        }

        if (!Attendance.isValidDate(date)) {
            throw new IllegalValueException("Invalid date format");
        }
        if (!Attendance.isValidAttendanceStatus(attendanceStatus)) {
            throw new IllegalValueException("Invalid attendance status format");
        }
        if (!Feedback.isValidFeedback(feedback)) {
            throw new IllegalValueException(Feedback.MESSAGE_CONSTRAINTS);
        }

        return new Attendance(date, attendanceStatus, new Feedback(feedback));
    }

}
