package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.exam.Exam;

public class JsonAdaptedAcademic {

    public static final String MISSING_ADMIN_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final List<JsonAdaptedAttendance> attendanceList = new ArrayList<>();
    private final List<JsonAdaptedExam> jsonAdaptedExams = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAcademic} with academic details.
     * @param attendanceList
     */
    @JsonCreator
    public JsonAdaptedAcademic(@JsonProperty("attendanceList") List<JsonAdaptedAttendance> attendanceList,
                               @JsonProperty("exams") List<JsonAdaptedExam> exams) {
        if (attendanceList != null) {
            this.attendanceList.addAll(attendanceList);
        }
        if (exams != null) {
            this.jsonAdaptedExams.addAll(exams);
        }
    }

    /**
     * Converts a given {@code Academic} into this class for Jackson use.
     */
    public JsonAdaptedAcademic(Student source) {
        attendanceList.addAll(source.getAttendance().stream()
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
        jsonAdaptedExams.addAll(source.getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted admin object into the model's {@code Admin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted admin.
     */
    public Academic toModelType() throws IllegalValueException {

        final List<Attendance> attendances = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attendanceList) {
            attendances.add(attendance.toModelType());
        }

        List<Exam> exams = new ArrayList<>();
        for (JsonAdaptedExam exam : jsonAdaptedExams) {
            exams.add(exam.toModelType());
        }

        final List<Attendance> modelAttendance = new ArrayList<>(attendances);
        return new Academic(modelAttendance, exams);
    }
}
