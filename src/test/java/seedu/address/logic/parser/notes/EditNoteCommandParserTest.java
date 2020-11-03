package seedu.address.logic.parser.notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EXISTENTIAL_CRISIS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GRADUATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_EXISTENTIAL_CRISIS;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_GRADUATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_GRADUATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.EditNoteCommand;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Title;
import seedu.address.testutil.notes.EditNoteDescriptorBuilder;

public class EditNoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE);

    private EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditNoteCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_GRADUATION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_GRADUATION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC,
                Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description

        // invalid title followed by valid description
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + DESCRIPTION_DESC_GRADUATION,
                Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid description
        assertParseFailure(parser, "1" + TITLE_DESC_GRADUATION + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS;

        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptorBuilder()
                .withTitle(VALID_TITLE).withDescription(VALID_DESCRIPTION).build();

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_SECOND;

        // title
        String userInput = targetIndex.getOneBased() + TITLE_DESC_EXISTENTIAL_CRISIS;
        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptorBuilder()
                .withTitle(VALID_TITLE).build();
        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_EXISTENTIAL_CRISIS;
        editNoteDescriptor = new EditNoteDescriptorBuilder().withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_GRADUATION + TITLE_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS;

        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptorBuilder()
                .withTitle(VALID_TITLE).withDescription(VALID_DESCRIPTION).build();

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS;
        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION).build();

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TITLE_DESC_GRADUATION + INVALID_DESCRIPTION_DESC
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS;
        editNoteDescriptor = new EditNoteDescriptorBuilder().withTitle(VALID_TITLE_GRADUATION)
                .withDescription(VALID_DESCRIPTION).build();

        expectedCommand = new EditNoteCommand(targetIndex, editNoteDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
