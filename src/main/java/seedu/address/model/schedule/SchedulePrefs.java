package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SchedulePrefs {
    private static final LocalTime DEFAULT_TIME = LocalTime.of(12, 0);
    private ScheduleViewMode viewMode;
    private LocalDateTime viewDateTime;

    /**
     * Creates a schedule preferences which encompass the user's preferred view mode and view date time.
     * @param viewMode
     * @param viewDate
     */
    public SchedulePrefs(ScheduleViewMode viewMode, LocalDate viewDate) {
        requireAllNonNull(viewMode, viewDate);
        this.viewMode = viewMode;
        this.viewDateTime = LocalDateTime.of(viewDate, DEFAULT_TIME);
    }

    public ScheduleViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ScheduleViewMode viewMode) {
        requireNonNull(viewMode);
        this.viewMode = viewMode;
    }

    public LocalDateTime getViewDateTime() {
        return viewDateTime;
    }

    public void setViewDateTime(LocalDate viewDate) {
        requireNonNull(viewDate);
        this.viewDateTime = LocalDateTime.of(viewDate, DEFAULT_TIME);
    }

    /**
     * Returns true if both SchedulePrefs have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SchedulePrefs)) {
            return false;
        }

        SchedulePrefs otherPrefs = (SchedulePrefs) other;
        return otherPrefs.getViewDateTime().equals(getViewDateTime())
                && otherPrefs.getViewMode().equals(getViewMode());
    }

    /**
     * Formats the event schedule pref object into a printable string format
     * @return a string representation of the event schedule prefs object
     */
    @Override
    public String toString() {
        return viewMode.name() + "_" + viewDateTime.toLocalDate().toString();
    }
}
