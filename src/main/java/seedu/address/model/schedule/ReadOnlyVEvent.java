package seedu.address.model.schedule;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;

/**
An unmodifiable view
 */
public interface ReadOnlyVEvent {
    /**
     * Returns an unmodifiable view of the VEvents list.
     * This list will not contain any duplicate VEvents.
     */
    ObservableList<VEvent> getVEvents();
}
