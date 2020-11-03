package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Note;
import seedu.address.model.schedule.ReadOnlyEvent;
import seedu.address.model.schedule.SchedulePrefs;
import seedu.address.model.schedule.ScheduleViewMode;
import seedu.address.model.schedule.Scheduler;
import seedu.address.model.student.NameComparator;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Reeve reeve;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final Scheduler scheduler;
    private final SchedulePrefs schedulePrefs;
    private final SortedList<Student> sortedStudents;
    private final Notebook notebook;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs and notebook.
     */
    public ModelManager(ReadOnlyReeve addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyNotebook notebook) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with reeve: " + addressBook + " , user prefs " + userPrefs + "and "
            + "notebook: " + notebook);

        this.reeve = new Reeve(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.reeve.getStudentList());
        this.scheduler = new Scheduler();
        this.schedulePrefs = new SchedulePrefs(ScheduleViewMode.WEEKLY, LocalDate.now());
        sortedStudents = new SortedList<>(this.filteredStudents, new NameComparator());
        this.notebook = new Notebook(notebook);
    }

    public ModelManager() {
        this(new Reeve(), new UserPrefs(), new Notebook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setReeve(ReadOnlyReeve reeve) {
        this.reeve.resetData(reeve);
    }

    @Override
    public ReadOnlyReeve getReeve() {
        return reeve;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return reeve.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        reeve.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        reeve.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        reeve.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== schedule ================================================================================

    @Override
    public ReadOnlyEvent getSchedule() {
        return scheduler;
    }

    @Override
    public LocalDateTime getScheduleViewDateTime() {
        return schedulePrefs.getViewDateTime();
    }

    @Override
    public void setScheduleViewDate(LocalDate viewDate) {
        schedulePrefs.setViewDateTime(viewDate);
    }

    @Override
    public ScheduleViewMode getScheduleViewMode() {
        return schedulePrefs.getViewMode();
    }

    @Override
    public void setScheduleViewMode(ScheduleViewMode viewMode) {
        schedulePrefs.setViewMode(viewMode);
    }

    @Override
    public ObservableList<VEvent> getVEventList() {
        return scheduler.getVEvents();
    }

    //=========== Sorted Student List Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getSortedStudentList() {
        return sortedStudents;
    }

    @Override
    public void updateSortedStudentList(Comparator<? super Student> comparator) {
        requireNonNull(comparator);
        sortedStudents.setComparator(comparator);
    }

    //=========== Notebook =====================================================================================

    @Override
    public void setNotebook(ReadOnlyNotebook notebook) {
        this.notebook.resetData(notebook);
    }

    @Override
    public ReadOnlyNotebook getNotebook() {
        return notebook;
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notebook.hasNote(note);
    }

    @Override
    public void deleteNote(Note target) {
        notebook.removeNote(target);
    }

    @Override
    public void addNote(Note note) {
        notebook.addNote(note);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);
        notebook.setNote(target, editedNote);
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return reeve.equals(other.reeve)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && notebook.equals(other.notebook);
    }

    @Override
    public void updateClassTimesToEvent() {
        scheduler.mapClassTimesToLessonEvent(reeve.getStudentList());
    }

    @Override
    public ObservableList<VEvent> getLessonEventsList() {
        updateClassTimesToEvent();
        return scheduler.getVEvents();
    }

    @Override
    public boolean isClashingClassTime(Student toCheck) {
        return scheduler.isClashingClassTime(toCheck, reeve.getStudentList());
    }
}

