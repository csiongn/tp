package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;

public class JsonAdaptedAdminTest {
    private static final String INVALID_CLASS_VENUE = " "; // blank
    private static final String INVALID_CLASS_TIME = "000";
    private static final String INVALID_FEE = "10.!#0";
    private static final String INVALID_PAYMENT_DATE = "0000";
    private static final String INVALID_ADDITIONAL_DETAIL = "!@#";

    private static final String VALID_CLASS_VENUE = BOB.getClassVenue().toString();
    private static final String VALID_CLASS_TIME = BOB.getClassTime().convertClassTimeToUserInputString();
    private static final String VALID_FEE = BOB.getFee().convertFeeToUserInputString();
    private static final String VALID_PAYMENT_DATE = BOB.getPaymentDate().convertPaymentDateToUserInputString();
    private static final List<JsonAdaptedDetail> VALID_ADDITIONAL_DETAILS =
            BOB.getDetails().stream().map(JsonAdaptedDetail::new).collect(Collectors.toList());



    @Test
    public void toModelType_validAdmin_returnsAdmin() throws Exception {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(BOB);
        assertEquals(BOB.getAdmin(), admin.toModelType());
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(INVALID_CLASS_VENUE, VALID_CLASS_TIME, VALID_FEE,
                VALID_PAYMENT_DATE, VALID_ADDITIONAL_DETAILS);
        String expectedMessage = ClassVenue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(null, VALID_CLASS_TIME, VALID_FEE, VALID_PAYMENT_DATE,
                VALID_ADDITIONAL_DETAILS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassVenue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, INVALID_CLASS_TIME, VALID_FEE,
                VALID_PAYMENT_DATE, VALID_ADDITIONAL_DETAILS);
        String expectedMessage = ClassTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, null, VALID_FEE, VALID_PAYMENT_DATE,
                VALID_ADDITIONAL_DETAILS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_invalidFee_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, VALID_CLASS_TIME, INVALID_FEE,
                VALID_PAYMENT_DATE, VALID_ADDITIONAL_DETAILS);
        String expectedMessage = Fee.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_nullFee_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, VALID_CLASS_TIME, null, VALID_PAYMENT_DATE,
                VALID_ADDITIONAL_DETAILS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Fee.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_invalidPaymentDate_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, VALID_CLASS_TIME, VALID_FEE,
                INVALID_PAYMENT_DATE, VALID_ADDITIONAL_DETAILS);
        String expectedMessage = PaymentDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_nullPaymentDate_throwsIllegalValueException() {
        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(VALID_CLASS_VENUE, VALID_CLASS_TIME, VALID_FEE, null,
                VALID_ADDITIONAL_DETAILS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PaymentDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }

    @Test
    public void toModelType_invalidAdditionalDetails_throwsIllegalValueException() {

        List<JsonAdaptedDetail> invalidDetails = new ArrayList<>(VALID_ADDITIONAL_DETAILS);
        invalidDetails.add(new JsonAdaptedDetail(INVALID_ADDITIONAL_DETAIL));

        JsonAdaptedAdmin admin = new JsonAdaptedAdmin(INVALID_CLASS_VENUE, VALID_CLASS_TIME, VALID_FEE,
                VALID_PAYMENT_DATE, invalidDetails);
        String expectedMessage = Detail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, admin::toModelType);
    }
}
