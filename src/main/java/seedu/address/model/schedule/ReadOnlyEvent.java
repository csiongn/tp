package seedu.address.model.schedule;

import java.util.List;

/**
 * Unmodifiable view of an Schedule comprising of VEvents
 */
public interface ReadOnlyEvent {

    /**
     * Returns an unmodifiable view of the Event list.
     * This list will not contain any duplicate Events.
     */
    List<Event> getEventsList();
}
