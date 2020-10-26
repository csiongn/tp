package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Note;
import seedu.address.model.student.NameComparator;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * An instance of {@code NameComparator}
     */
    Comparator<Student> COMPARATOR_NAME = new NameComparator();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setReeve(ReadOnlyReeve reeve);

    /**
     * Returns the AddressBook
     */
    ReadOnlyReeve getReeve();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns an unmodifiable view of the sorted person list
     */
    ObservableList<Student> getSortedStudentList();

    /**
     * Sorts the internal list in reeve by the given {@code comparator}
     */
    void updateSortedStudentList(Comparator<? super Student> cmp);

    /**
     * Replaces notebook data with the data in {@code notebook}.
     */
    void setNotebook(ReadOnlyNotebook notebook);

    /**
     * Returns the Notebook
     */
    ReadOnlyNotebook getNotebook();

    /**
     * Returns true if a note with the same title as {@code note} exists in the notebook.
     */
    boolean hasNote(Note note);

    /**
     * Deletes the given note.
     * The note must exist in the notebook.
     */
    void deleteNote(Note target);

    /**
     * Adds the given note.
     * {@code note} must not already exist in the notebook.
     */
    void addNote(Note note);

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the notebook.
     * The person identity of {@code editedNote} must not be the same as another existing person in the notebook.
     */
    void setNote(Note target, Note editedNote);

}
