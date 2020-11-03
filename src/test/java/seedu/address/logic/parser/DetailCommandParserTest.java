package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_DETAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddDetailCommand;
import seedu.address.logic.commands.DeleteDetailCommand;
import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.EditDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.admin.Detail;

public class DetailCommandParserTest {

    //@@author VaishakAnand
    private static final String ADD_DETAIL_DESC = AddDetailCommand.COMMAND_WORD + " ";
    private static final String EDIT_DETAIL_DESC = EditDetailCommand.COMMAND_WORD + " ";
    private static final String DELETE_DETAIL_DESC = DeleteDetailCommand.COMMAND_WORD + " ";

    private final DetailCommandParser parser = new DetailCommandParser();

    @Test
    public void parse_addDetailAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String userInput = ADD_DETAIL_DESC + targetStudentIndex.getOneBased() + ADDITIONAL_DETAIL_DESC_AMY;
        AddDetailCommand expectedCommand = new AddDetailCommand(targetStudentIndex,
                new Detail(VALID_ADDITIONAL_DETAILS_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addDetailMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddDetailCommand.MESSAGE_USAGE);
        Index targetStudentIndex = INDEX_SECOND_PERSON;

        // missing index and prefix
        assertParseFailure(parser, ADD_DETAIL_DESC, expectedMessage);

        // valid index, missing prefix
        assertParseFailure(parser, ADD_DETAIL_DESC + targetStudentIndex + " ", expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, ADD_DETAIL_DESC + ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);
    }

    @Test
    public void parse_deleteDetailAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = " " + PREFIX_INDEX + "2";
        String userInput = DELETE_DETAIL_DESC + targetStudentIndex.getOneBased() + targetDetailIndexDesc;
        DeleteDetailCommand expectedCommand = new DeleteDetailCommand(targetStudentIndex,
                Index.fromOneBased(2));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteDetailMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteDetailCommand.MESSAGE_USAGE);

        // missing 2 arguments
        assertParseFailure(parser, DELETE_DETAIL_DESC, expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, DELETE_DETAIL_DESC + "2", expectedMessage);

        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_INDEX, "2");
        assertParseFailure(parser, DELETE_DETAIL_DESC + targetDetailIndexDesc, expectedMessage);

        // wrong detail index
        String invalidDetailIndex = " " + PREFIX_INDEX + "0";
        assertParseFailure(parser, DELETE_DETAIL_DESC + "2" + invalidDetailIndex, expectedMessage);
    }

    @Test
    public void parse_editDetailAllFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = Index.fromOneBased(2);
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_INDEX, "2");
        String userInput = EDIT_DETAIL_DESC + "2" + targetDetailIndexDesc + ADDITIONAL_DETAIL_DESC_AMY;

        EditDetailCommand expectedCommand = new EditDetailCommand(targetStudentIndex,
                targetDetailIndex, new Detail(VALID_ADDITIONAL_DETAILS_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editDetailMissingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditDetailCommand.MESSAGE_USAGE);

        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_INDEX, targetDetailIndex.getOneBased());

        // missing 3 arguments
        assertParseFailure(parser, EDIT_DETAIL_DESC, expectedMessage);

        // missing 2 arguments
        assertParseFailure(parser, EDIT_DETAIL_DESC + "2", expectedMessage);
        assertParseFailure(parser, EDIT_DETAIL_DESC + targetDetailIndexDesc, expectedMessage);
        assertParseFailure(parser, EDIT_DETAIL_DESC + ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, EDIT_DETAIL_DESC + "2" + ADDITIONAL_DETAIL_DESC_AMY,
                expectedMessage);
        assertParseFailure(parser, EDIT_DETAIL_DESC + "2" + targetDetailIndexDesc,
                expectedMessage);
        assertParseFailure(parser, EDIT_DETAIL_DESC + targetDetailIndexDesc + ADDITIONAL_DETAIL_DESC_AMY,
                expectedMessage);

        // wrong detail index
        assertParseFailure(parser, EDIT_DETAIL_DESC + targetStudentIndex.getOneBased() + " "
                + PREFIX_INDEX + "0"
                + ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> parser.parse("unknownCommand"));
    }
    //@@author

}
