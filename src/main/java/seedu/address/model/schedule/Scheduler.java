package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.ClassTime;

/**
 * This class provides the basic functionalities of event operations.
 * Mapping of local Event to jfxtras's VEvent is contained here.
 */
public class Scheduler implements ReadOnlyEvent, ReadOnlyVEvent {

    private final ObservableList<VEvent> internalList = FXCollections.observableArrayList();
    private final ObservableList<VEvent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public Scheduler() {}

    /**
     * Creates a Scheduler using the list of events in the {@code lstOfEvents}
     */
    public Scheduler(List<Event> lstOfEvents) {
        requireNonNull(lstOfEvents);
        resetData(lstOfEvents);
    }


    /** Replaces the contents of this list with {@code vEvents}.
     * {@code vEvents} must not contain duplicate VEvent.
     */
    public void setVEvents(List<VEvent> vEvents) {
        requireAllNonNull(vEvents);
        this.internalList.setAll(vEvents);
    }

    /**
     * Resets the existing data of this {@code Scheduler} with {@code newData}.
     */
    public void resetData(List<Event> newData) {
        requireNonNull(newData);
        setVEvents(Mapper.mapListOfEventsToVEvent(newData));
    }

    /**
     * Adds a {@code eventToAdd} to Scheduler.
     * The {@code eventToAdd} must not already exist in Scheduler.
     */
    public void addEvent(Event eventToAdd) {
        requireNonNull(eventToAdd);
        internalList.add(Mapper.mapEventToVEvent(eventToAdd));
    }

    /**
     * Adds a list of events to the scheduler's list
     * @param lst
     */
    public void addListOfEvents(List<Event> lst) {
        requireNonNull(lst);
        resetData(lst);
    }

    @Override
    public String toString() {
        return internalUnmodifiableList.size() + " Lessons in Schedule";
    }

    @Override
    public ObservableList<VEvent> getVEvents() {
        return this.internalUnmodifiableList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }

        if (!(other instanceof Scheduler)) {
            return false;
        }

        Scheduler otherScheduler = (Scheduler) other;
        ObservableList<VEvent> otherList = otherScheduler.getVEvents();
        if (otherList.size() != this.internalList.size()) {
            return false;
        }

        return (new HashSet<>(otherList).equals(new HashSet<>(this.internalList)));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public List<Event> getEventsList() {
        return Mapper.mapListOfVEventsToEvent(this.internalList);
    }

    /**
     * Given a list of students, creates a list of Lesson events based on class time
     * @param studentList
     */
    public void mapClassTimesToLessonEvent(List<Student> studentList) {
        List<Event> listOfEvents = new ArrayList<>();
        for (Student student: studentList) {
            LocalDate date = student.getAdmin().getPaymentDate().lastPaid;

            // date of lesson event will be recorded from latest payment date onwards
            LocalDateTime ld = LocalDateTime.of(date, LocalTime.now());
            listOfEvents.add(LessonEvent.createLessonEvent(student, ld));
        }
        this.resetData(listOfEvents);
    }

    /**
     * Checks if the student {@code toCheck} has clashing classtimes with student in {@code studentList}.
     * @return true if student has clashing class time.
     */
    public boolean isClashingClassTime(Student toCheck, List<Student> studentList) {
        requireAllNonNull(toCheck, studentList);
        ClassTime toCheckClassTime = toCheck.getAdmin().getClassTime();
        for (Student currStd : studentList) {
            ClassTime currClassTime = currStd.getAdmin().getClassTime();
            if (toCheckClassTime.dayOfWeek.equals(currClassTime.dayOfWeek)) {
                LocalTime currStart = currClassTime.startTime;
                LocalTime currEnd = currClassTime.endTime;

                LocalTime toCheckStart = toCheckClassTime.startTime;
                LocalTime toCheckEnd = toCheckClassTime.endTime;

                if (currEnd.isAfter(toCheckStart) && currStart.isBefore(toCheckEnd)) {
                    return true;
                }

            }

        }
        return false;

    }

}
