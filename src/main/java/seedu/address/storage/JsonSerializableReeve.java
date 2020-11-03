package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.student.Student;

/**
 * An Immutable Reeve that is serializable to JSON format.
 */
@JsonRootName(value = "reeve")
class JsonSerializableReeve {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableReeve} with the given students.
     */
    @JsonCreator
    public JsonSerializableReeve(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyReeve} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableReeve}.
     */
    public JsonSerializableReeve(ReadOnlyReeve source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Reeve} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Reeve toModelType() throws IllegalValueException {
        Reeve reeve = new Reeve();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (reeve.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            reeve.addStudent(student);
        }
        return reeve;
    }

}
