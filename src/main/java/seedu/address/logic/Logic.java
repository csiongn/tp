package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDateTime;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.schedule.ScheduleViewMode;
import seedu.address.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getReeve()
     */
    ReadOnlyReeve getAddressBook();

    /**
     * Returns the notebook.
     */
    ReadOnlyNotebook getNotebook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredPersonList();

    /** Returns an unmodifiable view of the sorted list of students */
    ObservableList<Student> getSortedStudentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the preferred schedule view on a particular date and time.
     */
    LocalDateTime getScheduleViewDateTime();

    /**
     * Returns the preferred view mode, either week or day view.
     */
    ScheduleViewMode getScheduleViewMode();

    /**
     * Returns unmodifiable view of VEvent lesson.
     */
    ObservableList<VEvent> getVEventList();

}
