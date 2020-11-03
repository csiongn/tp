package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SCHOOL = " ";
    private static final String INVALID_YEAR = "F!ck!";

    private static final String VALID_NAME = BOB.getName().toString();
    private static final String VALID_PHONE = BOB.getPhone().toString();
    private static final String VALID_SCHOOL = BOB.getSchool().toString();
    private static final String VALID_YEAR = BOB.getYear().toString();

    private static final JsonAdaptedAdmin JSON_ADAPTED_ADMIN = new JsonAdaptedAdmin(BOB);
    private static final List<JsonAdaptedQuestion> QUESTION_LIST = BOB.getQuestions()
            .stream()
            .map(JsonAdaptedQuestion::new)
            .collect(Collectors.toList());
    private static final JsonAdaptedAcademic JSON_ADAPTED_ACADEMIC = new JsonAdaptedAcademic(BOB);

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BOB);
        Student bob = BOB;
        assertEquals(bob, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_SCHOOL, VALID_YEAR,
                        JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_SCHOOL, VALID_YEAR,
                JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_SCHOOL, VALID_YEAR,
                        JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_SCHOOL, VALID_YEAR,
                JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidSchool_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_SCHOOL, VALID_YEAR,
                        JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = School.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullSchool_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_YEAR,
                JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, School.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_SCHOOL, INVALID_YEAR,
                        JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_SCHOOL, null,
                JSON_ADAPTED_ADMIN, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAdmin_throwsNullPointerException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_SCHOOL, VALID_YEAR,
                null, QUESTION_LIST, JSON_ADAPTED_ACADEMIC);
        assertThrows(NullPointerException.class, student::toModelType);
    }

    @Test
    public void toModelType_nullAcademic_throwsNullPointerException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_SCHOOL, VALID_YEAR,
                JSON_ADAPTED_ADMIN, QUESTION_LIST, null);
        assertThrows(NullPointerException.class, student::toModelType);
    }
}
