package seedu.address.model.notes.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Note in the notebook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Note {

    private final Title title;
    private final Description description;

    /**
     *  title and description must be present and not null.
     */
    public Note(Title title, Description description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    private Note(Note copy) {
        requireNonNull(copy);
        this.title = copy.title;
        this.description = copy.description;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both note of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getTitle().toString().toLowerCase().equals(getTitle().toString().toLowerCase());
    }

    /**
     * Returns true if both note have the same title and description.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote.getTitle().equals(getTitle())
                && otherNote.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append("\nDescription: ")
                .append(getDescription());

        return builder.toString();
    }

}
