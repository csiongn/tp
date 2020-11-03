package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;

public class LessonEvent extends Event {

    public static final DateTimeFormatter VIEW_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Creates an Lesson Event object that will be link to the user's schedule.
     * This is based on the student's admin class time.
     * @param eventName
     * @param eventStartDateTime
     * @param eventEndDateTime
     * @param uniqueIdentifier
     * @param recurrence
     */
    private LessonEvent(String eventName, LocalDateTime eventStartDateTime, LocalDateTime eventEndDateTime,
                   String uniqueIdentifier, EventRecurrence recurrence) {
        super(eventName, eventStartDateTime, eventEndDateTime, uniqueIdentifier, recurrence);
    }

    /**
     * Creates a lesson event based on the student class time.
     * @param student student where lesson event is created for.
     * @param dateTimeCreated time the student is added to reeve.
     */
    public static LessonEvent createLessonEvent(Student student, LocalDateTime dateTimeCreated) {
        Admin studentAdmin = student.getAdmin();
        ClassTime classTime = studentAdmin.getClassTime();
        String studentName = student.getName().fullName;

        DayOfWeek classDayOfWeek = classTime.dayOfWeek;
        LocalDateTime lessonStart = computeLessonStartDate(dateTimeCreated, classDayOfWeek);

        LocalTime classTimeStart = classTime.startTime;
        LocalTime classTimeEnd = classTime.endTime;

        LocalDate lessonDate = lessonStart.toLocalDate();
        LocalDateTime lessonEventDateTimeStart = LocalDateTime.of(lessonDate, classTimeStart);
        LocalDateTime lessonEventDateTimeEnd = LocalDateTime.of(lessonDate, classTimeEnd);

        String uid = UniqueIdentifierGenerator.generateUid(studentName,
                lessonEventDateTimeStart.toString(), lessonEventDateTimeEnd.toString());

        return new LessonEvent(studentName, lessonEventDateTimeStart, lessonEventDateTimeEnd,
                uid, EventRecurrence.WEEKLY);
    }

    /**
     * This method adjust the date where the student is added and outputs the correct date for the first lesson.
     * @param dateCreated date where student is added.
     * @param classDay day where the student attends lesson.
     */
    public static LocalDateTime computeLessonStartDate(LocalDateTime dateCreated, DayOfWeek classDay) {
        requireAllNonNull(dateCreated, classDay);
        TemporalAdjuster adjuster = TemporalAdjusters.nextOrSame(classDay);
        if (dateCreated.isBefore(LocalDateTime.now())) {
            TemporalAdjuster adjustToNextWeek = TemporalAdjusters.next(classDay);
            return LocalDateTime.from(adjustToNextWeek.adjustInto(dateCreated));
        }
        return LocalDateTime.from(adjuster.adjustInto(dateCreated));
    }

}
