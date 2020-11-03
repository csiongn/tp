package seedu.address.testutil;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Event;
import seedu.address.model.schedule.EventRecurrence;
import seedu.address.model.schedule.Scheduler;

public class TypicalEvents {

    public static final LocalDateTime DATE_TIME_BEFORE_ALICE_CLASS_EVENT = LocalDateTime.parse("2020-12-03T10:16:30");

    public static final Event ALICE_CLASS_EVENT = new EventBuilder().withEventName("Lesson event")
            .withEventStartDateTime(LocalDateTime.parse("2020-12-03T10:15:30"))
            .withEventEndDateTime(LocalDateTime.parse("2020-12-03T10:17:30"))
            .withUniqueIdentifier("uidAliceLesson")
            .withRecurrence(EventRecurrence.WEEKLY)
            .build();

    public static final Event TODO_EVENT = new EventBuilder().withEventName("Plan lessons")
            .withEventStartDateTime(LocalDateTime.parse("2020-11-03T10:14:30"))
            .withEventEndDateTime(LocalDateTime.parse("2020-11-03T10:16:35"))
            .withUniqueIdentifier("uidPlanLesson")
            .withRecurrence(EventRecurrence.DAILY)
            .build();

    public static final Event RELAX_EVENT = new EventBuilder().withEventName("Watch a movie")
            .withEventStartDateTime(LocalDateTime.parse("2020-11-25T10:10:00"))
            .withEventEndDateTime(LocalDateTime.parse("2020-11-26T10:12:01"))
            .withUniqueIdentifier("uidWatchMovie")
            .withRecurrence(EventRecurrence.NONE)
            .build();

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ALICE_CLASS_EVENT, TODO_EVENT, RELAX_EVENT));
    }

    /**
     * Returns an {@code scheduler} with all the typical events.
     */
    public static Scheduler getTypicalScheduler() {
        return new Scheduler(getTypicalEvents());
    }
}
