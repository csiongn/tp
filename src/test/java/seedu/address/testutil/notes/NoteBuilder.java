package seedu.address.testutil.notes;

import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_TITLE = "meaning of life";
    public static final String DEFAULT_DESCRIPTION = "Eat sleep code? :'(";

    // Identity fields
    private Title title;
    private Description description;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        title = noteToCopy.getTitle();
        description = noteToCopy.getDescription();
    }

    /**
     * Sets the {@code Title} of the {@code Note} that we are building.
     */
    public NoteBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }


    /**
     * Sets the {@code Description} of the {@code Note} that we are building.
     */
    public NoteBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Builds a {@code Note} based on the given information.
     */
    public Note build() {
        return new Note(title, description);
    }

}
