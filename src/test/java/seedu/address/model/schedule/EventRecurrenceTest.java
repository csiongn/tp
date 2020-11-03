package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EventRecurrenceTest {

    @Test
    public void checkWhichRecurrence_validInput_success() throws Exception {
        assertEquals(EventRecurrence.DAILY, EventRecurrence.checkWhichRecurrence("Daily"));
        assertEquals(EventRecurrence.WEEKLY, EventRecurrence.checkWhichRecurrence("Weekly"));
        assertEquals(EventRecurrence.NONE, EventRecurrence.checkWhichRecurrence("None"));
    }

    @Test
    public void checkWhichRecurrence_invalidInput_throwsIllegalArgumentException() {
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurrence(""));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurrence("   "));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurrence("123"));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurrence("hahahah"));
    }

    @Test
    public void checkWhichRecurRule_validInput_success() throws Exception {
        assertEquals(EventRecurrence.DAILY, EventRecurrence.checkWhichRecurRule("FREQ=DAILY;INTERVAL=1"));
        assertEquals(EventRecurrence.WEEKLY, EventRecurrence.checkWhichRecurRule("FREQ=WEEKLY;INTERVAL=1"));
        assertEquals(EventRecurrence.NONE, EventRecurrence.checkWhichRecurRule("FREQ=YEARLY;INTERVAL=1"));
    }

    @Test
    public void checkWhichRecurRulee_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurRule("   "));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurRule(""));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurRule("123"));
        assertThrows(ParseException.class, () -> EventRecurrence.checkWhichRecurRule("hahahah"));
    }

}
