package seedu.address.testutil.notes;

import seedu.address.logic.commands.notes.EditNoteCommand.EditNoteDescriptor;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;

/**
 * A utility class to help with building EditNoteDescriptor objects.
 */
public class EditNoteDescriptorBuilder {

    private EditNoteDescriptor descriptor;

    public EditNoteDescriptorBuilder() {
        descriptor = new EditNoteDescriptor();
    }

    public EditNoteDescriptorBuilder(EditNoteDescriptor descriptor) {
        this.descriptor = new EditNoteDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditNoteDescriptor} with fields containing {@code note}'s details
     */
    public EditNoteDescriptorBuilder(Note note) {
        descriptor = new EditNoteDescriptor();
        descriptor.setTitle(note.getTitle());
        descriptor.setDescription(note.getDescription());
    }

    /**
     * Sets the {@code Title} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditNoteDescriptor build() {
        return descriptor;
    }
}
