package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class LessonEventTest {

    @Test
    public void computeLessonStartDate_dateCreatedDayIsAfterDayOfLesson_returnsNextWeekDate() {
        // this is a thursday
        LocalDateTime dummyDateCreated = LocalDateTime.of(2020, 10, 29, 9, 0);
        DayOfWeek dayOfClass = DayOfWeek.WEDNESDAY;

        LocalDateTime expected = dummyDateCreated.plusWeeks(1).minusDays(1); // lesson start next wednesday

        LocalDateTime result = LessonEvent.computeLessonStartDate(dummyDateCreated,
                dayOfClass);

        assertEquals(expected, result);

    }

    @Test
    public void computeLessonStartDate_dateCreatedDayIsBeforeDayOfLesson_returnsThisWeekDate() {
        // this is a thursday
        LocalDateTime dummyDateCreated = LocalDateTime.of(2020, 10, 29, 9, 0);

        // lesson will start on same week
        LocalDateTime expected = dummyDateCreated.plusDays(1);

        assertEquals(expected, LessonEvent.computeLessonStartDate(dummyDateCreated, DayOfWeek.FRIDAY));
    }
}
