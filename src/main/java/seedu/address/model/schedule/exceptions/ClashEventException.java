package seedu.address.model.schedule.exceptions;

/**
 * Signals that the operation will result in clashing Events (Events are considered clash if they have the same
 * startDateTime and same endDateTime).
 */
public class ClashEventException extends RuntimeException {
    public ClashEventException() {
        super("Operation would result in clashing events time");
    }
}
