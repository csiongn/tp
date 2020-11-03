package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Mapper class that maps Events to VEvents and vice versa.
 */
public class Mapper {

    /**
     * Maps a local Event object to a VEvent for Jfxtras calendar
     */
    public static VEvent mapEventToVEvent(Event event) {
        requireNonNull(event);
        VEvent vEvent = new VEvent()
                .withSummary(event.getEventName())
                .withDateTimeStart(event.getEventStartDateTime())
                .withDateTimeEnd(event.getEventEndDateTime())
                .withUniqueIdentifier(event.getUniqueIdentifier());
        if (!(event.getRecurrence() == null) || !event.getRecurrence().equals(EventRecurrence.NONE)) {
            vEvent.setRecurrenceRule(event.getRecurrence().getVEventRecurRule());
        }
        if (event.getRecurrence().getVEventRecurRule() == "") {
            vEvent.setRecurrenceRule(EventRecurrence.NONE.getVEventRecurRule());
        }

        return vEvent;
    }

    /**
     * Maps a List of Event to VEvent
     * @param events the events to be mapped
     */
    public static List<VEvent> mapListOfEventsToVEvent(List<Event> events) {
        List<VEvent> vEvents = new ArrayList<>();
        for (Event event: events) {
            vEvents.add(mapEventToVEvent(event));
        }
        return vEvents;
    }

    /**
     * Maps a VEvent from jfxtras calendar to local Event object
     */
    public static Event mapVEventToEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        String eventName = vEvent.getSummary().getValue();
        LocalDateTime eventStartDateTime = LocalDateTime.from(vEvent.getDateTimeStart().getValue());
        LocalDateTime eventEndDateTime = LocalDateTime.from(vEvent.getDateTimeEnd().getValue());
        String uniqueIdentifier = vEvent.getUniqueIdentifier().getValue();

        EventRecurrence recurrence;
        try {
            if (vEvent.getRecurrenceRule().getValue() == null) {
                recurrence = EventRecurrence.NONE;
            } else {
                recurrence = EventRecurrence.checkWhichRecurRule(vEvent.getRecurrenceRule()
                        .getValue().toString());
            }
        } catch (ParseException e) {
            recurrence = EventRecurrence.NONE;
        }

        return new Event(eventName, eventStartDateTime, eventEndDateTime,
                uniqueIdentifier, recurrence);
    }

    /**
     * Maps a list of VEvent to Event.
     * @param vEvents the VEvents to be mapped
     */
    public static List<Event> mapListOfVEventsToEvent(List<VEvent> vEvents) {
        List<Event> events = new ArrayList<>();
        for (VEvent vEvent: vEvents) {
            events.add(mapVEventToEvent(vEvent));
        }

        return events;
    }



}
