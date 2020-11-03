package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.notes.note.Note;

/**
 * Panel containing the list of persons.
 */
public class Notebook extends UiPart<Region> {
    private static final String FXML = "Notebook.fxml";
    private final Logger logger = LogsCenter.getLogger(Notebook.class);

    @FXML
    private ListView<Note> notebook;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public Notebook(ObservableList<Note> notes) {
        super(FXML);
        notebook.setItems(notes);
        notebook.setCellFactory(listView -> new NoteCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Note} using a {@code NoteTile}.
     */
    class NoteCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteTile(note, getIndex() + 1).getRoot());
            }
        }
    }

}
