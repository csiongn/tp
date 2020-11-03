package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_VENUE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_VENUE_DESC_BOB;
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
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAdminDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.testutil.EditAdminDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_SCHOOL_DESC, School.MESSAGE_CONSTRAINTS); // invalid school
        assertParseFailure(parser, "1" + INVALID_YEAR_DESC, Year.MESSAGE_CONSTRAINTS); // invalid year
        assertParseFailure(parser, "1" + INVALID_VENUE_DESC, ClassVenue.MESSAGE_CONSTRAINTS); // invalid venue
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, ClassTime.MESSAGE_CONSTRAINTS); // invalid time
        assertParseFailure(parser, "1" + INVALID_FEE_DESC, Fee.MESSAGE_CONSTRAINTS); // invalid fee
        assertParseFailure(parser, "1" + INVALID_PAYMENT_DESC, PaymentDate.MESSAGE_CONSTRAINTS);
        // invalid payment date

        // invalid phone followed by valid school
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + SCHOOL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        // assertParseFailure(parser, "1" + DET_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        // assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        // assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SCHOOL_DESC + VALID_YEAR_AMY
                        + VALID_PHONE_AMY + INVALID_PAYMENT_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + YEAR_DESC_AMY
                + SCHOOL_DESC_AMY + NAME_DESC_AMY + FEE_DESC_BOB + PAYMENT_DATE_DESC_AMY
                + CLASS_TIME_DESC_BOB + CLASS_VENUE_DESC_AMY;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withSchool(VALID_SCHOOL_AMY)
                .withYear(VALID_YEAR_AMY).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder().withFee(VALID_FEE_BOB)
                .withPaymentDate(VALID_PAYMENT_DATE_AMY).withTime(VALID_CLASS_TIME_BOB)
                .withVenue(VALID_CLASS_VENUE_AMY).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + SCHOOL_DESC_AMY + PAYMENT_DATE_DESC_AMY;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withSchool(VALID_SCHOOL_AMY).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder()
                .withPaymentDate(VALID_PAYMENT_DATE_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // school
        userInput = targetIndex.getOneBased() + SCHOOL_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withSchool(VALID_SCHOOL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // year
        userInput = targetIndex.getOneBased() + YEAR_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder()
                .withYear(VALID_YEAR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // class time
        userInput = targetIndex.getOneBased() + CLASS_TIME_DESC_BOB;
        editStudentDescriptor = new EditStudentDescriptorBuilder().build();
        editAdminDescriptor = new EditAdminDescriptorBuilder().withTime(VALID_CLASS_TIME_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // class venue
        userInput = targetIndex.getOneBased() + CLASS_VENUE_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().build();
        editAdminDescriptor = new EditAdminDescriptorBuilder().withVenue(VALID_CLASS_VENUE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // fee
        userInput = targetIndex.getOneBased() + FEE_DESC_BOB;
        editStudentDescriptor = new EditStudentDescriptorBuilder().build();
        editAdminDescriptor = new EditAdminDescriptorBuilder().withFee(VALID_FEE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // payment date
        userInput = targetIndex.getOneBased() + PAYMENT_DATE_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().build();
        editAdminDescriptor = new EditAdminDescriptorBuilder().withVenue(VALID_PAYMENT_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY
                + PHONE_DESC_AMY + SCHOOL_DESC_AMY + YEAR_DESC_AMY + CLASS_TIME_DESC_BOB + CLASS_VENUE_DESC_AMY
                + CLASS_TIME_DESC_BOB + CLASS_VENUE_DESC_BOB + PHONE_DESC_BOB + SCHOOL_DESC_BOB + YEAR_DESC_BOB;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withSchool(VALID_SCHOOL_BOB).withYear(VALID_YEAR_BOB).build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder().withTime(VALID_CLASS_TIME_BOB)
                .withVenue(VALID_CLASS_VENUE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB + FEE_DESC_BOB;
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .build();
        EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder().withFee(VALID_FEE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + YEAR_DESC_BOB + INVALID_PHONE_DESC + SCHOOL_DESC_BOB
                + PHONE_DESC_BOB;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withYear(VALID_YEAR_BOB).withSchool(VALID_SCHOOL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor, editAdminDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
