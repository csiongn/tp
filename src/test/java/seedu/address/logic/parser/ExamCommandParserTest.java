package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.ExamCommand;
import seedu.address.logic.commands.ExamStatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

public class ExamCommandParserTest {

    private static final String ADD_EXAM_DESC = AddExamCommand.COMMAND_WORD + " ";
    private static final String DEL_EXAM_DESC = DeleteExamCommand.COMMAND_WORD + " ";

    private final ExamCommandParser parser = new ExamCommandParser();

    //@@author hogantan
    @Test
    public void parse_addExamAllFieldsPresent_success() {
        //all fields presents - single
        String userInput = ADD_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY;
        AddExamCommand expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam names - last name accepted
        userInput = ADD_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_BOB, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam dates - last date accepted
        userInput = ADD_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_EXAM_DATE
                + VALID_EXAM_DATE_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_BOB, new Score(VALID_EXAM_SCORE_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple exam scores - last score accepted
        userInput = ADD_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + EXAM_DESC_AMY + " " + PREFIX_SCORE
                + VALID_EXAM_SCORE_BOB;
        expectedCommand = new AddExamCommand(INDEX_SECOND_PERSON,
                new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_BOB)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addExamMissingArguments_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddExamCommand.MESSAGE_USAGE);

        // missing index and prefix
        assertParseFailure(parser, ADD_EXAM_DESC, expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, ADD_EXAM_DESC + EXAM_DESC_AMY, expectedMessage);

        // valid index, missing all three prefixes
        assertParseFailure(parser, ADD_EXAM_DESC + INDEX_SECOND_PERSON + " ", expectedMessage);

        // valid index, missing two prefixes
        assertParseFailure(parser, ADD_EXAM_DESC + INDEX_SECOND_PERSON + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_AMY, expectedMessage);

        // valid index, missing one prefixes
        assertParseFailure(parser, ADD_EXAM_DESC + INDEX_SECOND_PERSON + " " + PREFIX_EXAM_NAME
                + VALID_EXAM_NAME_AMY + " " + PREFIX_EXAM_DATE + VALID_EXAM_DATE_AMY, expectedMessage);
    }

    @Test
    public void parse_deleteExamAllFieldsPresent_success() {
        String targetExamIndexDesc = String.format(" %s%s", PREFIX_EXAM_INDEX, "2");
        String userInput = DEL_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + targetExamIndexDesc;
        DeleteExamCommand expectedCommand = new DeleteExamCommand(INDEX_SECOND_PERSON, Index.fromOneBased(2));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_deleteExamMissingArguments_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteExamCommand.MESSAGE_USAGE);

        String targetExamIndexDesc = String.format(" %s%s", PREFIX_EXAM_INDEX, INDEX_SECOND_PERSON.getOneBased());

        // missing 2 arguments
        assertParseFailure(parser, DEL_EXAM_DESC, expectedMessage);

        // missing 1 argument
        String badCommand = DEL_EXAM_DESC + String.format("%s", INDEX_SECOND_PERSON.getOneBased());
        assertParseFailure(parser, badCommand, expectedMessage);
        badCommand = DEL_EXAM_DESC + targetExamIndexDesc;
        assertParseFailure(parser, badCommand, expectedMessage);

        // wrong exam index
        badCommand = DEL_EXAM_DESC + INDEX_SECOND_PERSON.getOneBased() + " " + INDEX_SECOND_PERSON + "0";
        assertParseFailure(parser, badCommand, expectedMessage);
    }

    @Test
    public void parse_examStatsMissingIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExamStatsCommand.MESSAGE_USAGE);

        String missingIndexDesc = String.format("%s %s", ExamStatsCommand.COMMAND_WORD, " ");

        assertParseFailure(parser, missingIndexDesc, expectedMessage);
    }

    @Test
    public void parse_examStatsInvalidIndex_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExamStatsCommand.MESSAGE_USAGE);

        String missingIndexDesc = String.format("%s %s", ExamStatsCommand.COMMAND_WORD, "0");

        assertParseFailure(parser, missingIndexDesc, expectedMessage);
    }

    @Test
    public void parseCommand_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExamCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExamCommand.MESSAGE_USAGE), () -> parser.parse("unknownCommand"));
    }
    //@@author
}
