package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.student.Student;

/**
 * A Model stub that contains a single student.
 */
public class ModelStubWithStudent extends ModelStub {
    private final Student student;

    /**
     * {@code student} student inside the model
     */
    public ModelStubWithStudent(Student student) {
        requireNonNull(student);
        this.student = student;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return this.student.isSameStudent(student);
    }
}

