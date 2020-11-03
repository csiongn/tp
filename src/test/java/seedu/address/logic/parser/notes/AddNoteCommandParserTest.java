package seedu.address.logic.parser.notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EXISTENTIAL_CRISIS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GRADUATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_EXISTENTIAL_CRISIS;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_GRADUATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_EXISTENTIAL_CRISIS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.AddNoteCommand;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;
import seedu.address.testutil.notes.NoteBuilder;

public class AddNoteCommandParserTest {

    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_fieldsPresent_success() {
        Note expectedNote = new NoteBuilder(NOTE_EXISTENTIAL_CRISIS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS, new AddNoteCommand(expectedNote));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_GRADUATION + TITLE_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_EXISTENTIAL_CRISIS, new AddNoteCommand(expectedNote));

        // multiple description - last phone accepted
        assertParseSuccess(parser, TITLE_DESC_EXISTENTIAL_CRISIS
                + DESCRIPTION_DESC_GRADUATION + DESCRIPTION_DESC_EXISTENTIAL_CRISIS,
                new AddNoteCommand(expectedNote));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESCRIPTION_DESC_GRADUATION,
                Title.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_GRADUATION + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_GRADUATION
                        + DESCRIPTION_DESC_GRADUATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }
}
