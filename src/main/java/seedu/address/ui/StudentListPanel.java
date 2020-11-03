package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of persons.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);
    private boolean state;

    @FXML
    private ListView<Student> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        personListView.setItems(studentList);
        personListView.setCellFactory(listView -> new StudentListViewCell());
        state = true;
    }

    /**
     * Toggles between StudentAdminCard and StudentAcademicCard.
     */
    public void toggleState() {
        state = !state;
        personListView.refresh(); //To show immediate result of toggle
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (state) {
                    setGraphic(new StudentAdminCard(student, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new StudentAcademicCard(student, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
