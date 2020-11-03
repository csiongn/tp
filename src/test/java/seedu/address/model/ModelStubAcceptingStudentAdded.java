package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.student.Student;

/**
 * A Model stub that always accept the person being added.
 */
public class ModelStubAcceptingStudentAdded extends ModelStub {
    public final ArrayList<Student> personsAdded = new ArrayList<>();

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return personsAdded.stream().anyMatch(student::isSameStudent);
    }

    @Override
    public void addStudent(Student student) {
        requireNonNull(student);
        personsAdded.add(student);
    }

    @Override
    public boolean isClashingClassTime(Student toCheck) {
        return false;
    }

    @Override
    public ReadOnlyReeve getReeve() {
        return new Reeve();
    }
}
