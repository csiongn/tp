package seedu.address.storage.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Note;

/**
 * An Immutable Notebook that is serializable to JSON format.
 */
@JsonRootName(value = "notebook")
public class JsonSerializableNotebook {

    public static final String MESSAGE_DUPLICATE_NOTE = "Notebook contains duplicate note(s).";

    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNotebook} with the given students.
     */
    @JsonCreator
    public JsonSerializableNotebook(@JsonProperty("notes") List<JsonAdaptedNote> notes) {
        this.notes.addAll(notes);
    }

    /**
     * Converts a given {@code ReadOnlyNotebook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNotebook}.
     */
    public JsonSerializableNotebook(ReadOnlyNotebook source) {
        notes.addAll(source.getNotesList().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this notebook into the model's {@code Notebook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Notebook toModelType() throws IllegalValueException {
        Notebook notebook = new Notebook();
        for (JsonAdaptedNote jsonAdaptedNote : notes) {
            Note note = jsonAdaptedNote.toModelType();
            if (notebook.hasNote(note)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_NOTE);
            }
            notebook.addNote(note);
        }
        return notebook;
    }

}

