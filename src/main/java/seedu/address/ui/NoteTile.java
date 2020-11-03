package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Note}.
 */
public class NoteTile extends UiPart<Region> {

    private static final String FXML = "NoteTile.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final seedu.address.model.notes.note.Note note;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label description;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public NoteTile(seedu.address.model.notes.note.Note note, int displayedIndex) {
        super(FXML);
        this.note = note;
        id.setText(displayedIndex + ". ");
        title.setText(note.getTitle().title);
        description.setText(note.getDescription().description);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteTile)) {
            return false;
        }

        // state check
        NoteTile card = (NoteTile) other;
        return id.getText().equals(card.id.getText())
                && note.equals(card.note);
    }
}
