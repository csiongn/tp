package seedu.address.model.student.academic;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.student.academic.exam.Exam;

/**
 * Represents all academic details of a Student in Reeve.
 * Consists of exams and attendance.
 */
public class Academic {

    private final List<Attendance> attendance = new ArrayList<>();
    private final List<Exam> exams = new ArrayList<>();

    /**
     * Builds an Academic object.
     * @param attendance list of attendance for student.
     */
    public Academic(List<Attendance> attendance, List<Exam> exams) {
        requireAllNonNull(attendance);
        this.attendance.addAll(attendance);
        this.exams.addAll(exams);
    }

    public List<Attendance> getAttendance() {
        return new ArrayList<>(attendance);
    }

    public List<Exam> getExams() {
        return new ArrayList<>(exams);
    }

    /**
     * Get Attendance of student formatted for GUI use.
     * @return formatted exams.
     */
    public String getFormattedAttendance() {
        String result = "";
        int index = 1;
        for (Attendance singleAttendance : attendance) {
            result = result + index + ". " + singleAttendance + "\n";
            index++;
        }
        return result;
    }

    /**
     * Get exams of student formatted for GUI use.
     * @return formatted exams.
     */
    public String getFormattedExams() {
        String result = "";
        int index = 1;
        for (Exam exam : exams) {
            result = result + index + "." + exam.toString() + "\n";
            index++;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Academic)) {
            return false;
        }

        Academic other = (Academic) obj;
        return other.getAttendance().equals(getAttendance());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        if (!attendance.isEmpty()) {
            builder.append("\nAttendance:\n");
            String detailList = attendance.stream()
                    .map(detail -> String.format("- %s", detail))
                    .collect(Collectors.joining("\n"));
            builder.append(detailList);
        }

        if (!exams.isEmpty()) {
            builder.append("\nExams:\n");
            exams.forEach(builder::append);
        }

        return builder.toString();
    }

}
