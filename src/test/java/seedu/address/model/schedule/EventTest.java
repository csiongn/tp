package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.DATE_TIME_BEFORE_ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.TODO_EVENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {
    public static final String VALID_EVENT_NAME = "Valid event name";
    public static final LocalDateTime VALID_EVENT_START_DATE_TIME = LocalDateTime.of(2020, 1, 27, 0, 0);
    public static final LocalDateTime VALID_EVENT_END_DATE_TIME = LocalDateTime.of(2020, 1, 28, 0, 1);
    public static final String VALID_UNIQUE_IDENTIFIER = "This is a valid identifier";
    public static final EventRecurrence VALID_EVENT_RECURRENCE = EventRecurrence.DAILY;


    @Test
    public void constructor_nullEventName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullEventStartDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                null, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullEventEndDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, null,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullIdentifier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                null, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullRecurrence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceClassEventCopy = new EventBuilder(ALICE_CLASS_EVENT).build();
        assertTrue(ALICE_CLASS_EVENT.equals(aliceClassEventCopy));

        // same object -> returns true
        assertTrue(ALICE_CLASS_EVENT.equals(ALICE_CLASS_EVENT));

        // null -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(5));

        // different event -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(TODO_EVENT));

        // different event name -> returns false
        Event editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventName(TODO_EVENT.getEventName()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different startTime -> returns false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventStartDateTime(DATE_TIME_BEFORE_ALICE_CLASS_EVENT).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different endTime -> returns false
        editedAliceClassEvent =
                new EventBuilder(ALICE_CLASS_EVENT).withEventEndDateTime(DATE_TIME_BEFORE_ALICE_CLASS_EVENT).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different recurrence -> return false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withRecurrence(TODO_EVENT.getRecurrence()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different uniqueIdentifier -> return false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withUniqueIdentifier(TODO_EVENT.getUniqueIdentifier()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));
    }
}
