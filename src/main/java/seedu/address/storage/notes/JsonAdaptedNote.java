package seedu.address.storage.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;
import seedu.address.model.student.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedNote {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String title;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.getTitle().title;
        description = source.getDescription().description;
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        return new Note(modelTitle, modelDescription);
    }

}
