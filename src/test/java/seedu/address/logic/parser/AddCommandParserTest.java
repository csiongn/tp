package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_DETAILS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_DETAILS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_VENUE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_VENUE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FEE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FEE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SCHOOL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.student.admin.Fee.FREE_OF_CHARGE;
import static seedu.address.model.student.admin.PaymentDate.TODAY;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withQuestions()
                .withExams().withAttendances().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + SCHOOL_DESC_BOB + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        // multiple emails - last school accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_AMY + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        // multiple years - last year accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_AMY + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        //multiple venues - last venue accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_AMY + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        //multiple times - last time accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_AMY + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        //multiple fees - last fee accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_AMY + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        //multiple payment dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_AMY
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB, new AddCommand(expectedStudent));

        //multiple details - all details accepted
        expectedStudent = new StudentBuilder(BOB)
                .withDetails(VALID_ADDITIONAL_DETAILS_BOB, VALID_ADDITIONAL_DETAILS_AMY)
                .withQuestions()
                .withExams().withAttendances()
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_AMY
                + PAYMENT_DATE_DESC_BOB + ADDITIONAL_DETAILS_DESC_BOB + ADDITIONAL_DETAILS_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_allFieldsMissing_success() {
        Student expectedStudent = new StudentBuilder(AMY)
                .withQuestions().withDetails().withFee(FREE_OF_CHARGE).withPaymentDate(TODAY)
                .withExams().withAttendances().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY
                        + CLASS_VENUE_DESC_AMY + CLASS_TIME_DESC_AMY,
                new AddCommand(expectedStudent));

    }

    @Test
    public void parse_optionalFieldsPresent_success() {
        // payment date present
        Student expectedStudent = new StudentBuilder(AMY)
                .withQuestions().withDetails().withFee(FREE_OF_CHARGE)
                .withExams().withAttendances().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY
                        + CLASS_VENUE_DESC_AMY + CLASS_TIME_DESC_AMY + PAYMENT_DATE_DESC_AMY,
                new AddCommand(expectedStudent));

        // fee present
        expectedStudent = new StudentBuilder(AMY)
                .withQuestions().withDetails().withPaymentDate(TODAY)
                .withExams().withAttendances().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY
                        + CLASS_VENUE_DESC_AMY + CLASS_TIME_DESC_AMY + FEE_DESC_AMY,
                new AddCommand(expectedStudent));

        // details present
        expectedStudent = new StudentBuilder(AMY)
                .withQuestions().withFee(FREE_OF_CHARGE).withPaymentDate(TODAY)
                .withExams().withAttendances().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY
                        + CLASS_VENUE_DESC_AMY + CLASS_TIME_DESC_AMY + ADDITIONAL_DETAILS_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                + PAYMENT_DATE_DESC_BOB, expectedMessage);

        // missing school prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + YEAR_DESC_BOB
                + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB, expectedMessage);

        // missing year prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB, expectedMessage);

        // missing venue prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB, expectedMessage);

        //missing time prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid school
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_SCHOOL_DESC + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                School.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + INVALID_YEAR_DESC
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                Year.MESSAGE_CONSTRAINTS);

        //invalid venue
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                + INVALID_VENUE_DESC + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                ClassVenue.MESSAGE_CONSTRAINTS);

        //invalid time
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + INVALID_TIME_DESC + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                ClassTime.MESSAGE_CONSTRAINTS);

        //invalid fee
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + INVALID_FEE_DESC + PAYMENT_DATE_DESC_BOB,
                Fee.MESSAGE_CONSTRAINTS);

        //invalid payment date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + INVALID_PAYMENT_DESC,
                PaymentDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_SCHOOL_DESC + YEAR_DESC_BOB
                        + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB + PAYMENT_DATE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB
                        + YEAR_DESC_BOB + CLASS_VENUE_DESC_BOB + CLASS_TIME_DESC_BOB + FEE_DESC_BOB
                        + PAYMENT_DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
