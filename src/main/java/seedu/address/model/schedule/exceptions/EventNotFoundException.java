package seedu.address.model.schedule.exceptions;

/**
 * Signals that the operation is unable to find the specified Event.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Event cannot be found";
    }
}
