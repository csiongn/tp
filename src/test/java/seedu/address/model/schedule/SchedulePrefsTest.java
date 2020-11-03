package seedu.address.model.schedule;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SchedulePrefsTest {

    private LocalDate date = LocalDate.of(2020, 10, 29);
    private SchedulePrefs schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, date);
    @Test
    public void setViewMode_nullViewMode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewMode(null));
    }

    @Test
    public void setViewMode_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedulePrefs.setViewDateTime(null));
    }
}
