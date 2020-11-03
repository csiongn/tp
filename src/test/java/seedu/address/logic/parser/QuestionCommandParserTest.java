package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

public class QuestionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuestionCommand.MESSAGE_USAGE);

    private static final String ADD_QUESTION_DESC = AddQuestionCommand.COMMAND_WORD + " ";
    private static final String SOLVE_QUESTION_DESC = SolveQuestionCommand.COMMAND_WORD + " ";
    private static final String DEL_QUESTION_DESC = DeleteQuestionCommand.COMMAND_WORD + " ";

    private static final String QUESTION_INDEX_DESC = " " + PREFIX_INDEX + "1";
    private static final String SOLUTION_DESC = " " + PREFIX_TEXT + DEFAULT_SOLUTION;

    private QuestionCommandParser parser = new QuestionCommandParser();

    @Test
    public void parse_invalidCommandWord_failure() {
        String invalidWord = "random words";
        assertParseFailure(parser, invalidWord, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addQuestionMissingParts_failure() {
        // missing student index
        assertParseFailure(parser, ADD_QUESTION_DESC + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // missing student question
        assertParseFailure(parser, ADD_QUESTION_DESC + "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addQuestionInvalidPreamble_failure() {
        // invalid integers
        assertParseFailure(parser, ADD_QUESTION_DESC + "0" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, ADD_QUESTION_DESC + "-2" + QUESTION_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid argument
        assertParseFailure(parser, ADD_QUESTION_DESC + "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix parsed as preamble
        String invalidPrefix = " " + PREFIX_INDEX + VALID_QUESTION_AMY;
        assertParseFailure(parser, ADD_QUESTION_DESC + "1" + invalidPrefix, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_addQuestionInvalidQuestion_failure() {
        String invalidQuestion = " " + PREFIX_TEXT;
        assertParseFailure(parser, ADD_QUESTION_DESC + "1" + invalidQuestion, Question.MESSAGE_CONSTRAINTS);

        invalidQuestion = " " + PREFIX_TEXT + " ";
        assertParseFailure(parser, ADD_QUESTION_DESC + "1" + invalidQuestion, Question.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_addQuestionValidArguments_success() {
        String input = ADD_QUESTION_DESC + "1" + QUESTION_DESC_AMY;
        UnsolvedQuestion question = new UnsolvedQuestion(VALID_QUESTION_AMY);
        AddQuestionCommand command = new AddQuestionCommand(INDEX_FIRST_PERSON, question);
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_solveQuestionMissingParts_failure() {
        // missing student index
        assertParseFailure(parser, SOLVE_QUESTION_DESC + QUESTION_INDEX_DESC + SOLUTION_DESC,
                MESSAGE_INVALID_FORMAT);

        // missing question question
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + SOLUTION_DESC, MESSAGE_INVALID_FORMAT);

        // missing solution
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + QUESTION_INDEX_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_solveQuestionInvalidPreamble_failure() {
        // invalid integers
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "0" + QUESTION_INDEX_DESC + SOLUTION_DESC,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "-2" + QUESTION_INDEX_DESC + SOLUTION_DESC,
                MESSAGE_INVALID_FORMAT);

        // invalid argument
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix parsed as preamble
        String invalidPrefix = "1 p/ string";
        assertParseFailure(parser, SOLVE_QUESTION_DESC + invalidPrefix, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_solveQuestionInvalidQuestionIndex_failure() {
        // negative index
        String invalidIndex = " " + PREFIX_INDEX + "-5";
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + invalidIndex, MESSAGE_INVALID_FORMAT);

        // 0 index
        invalidIndex = " " + PREFIX_INDEX + "0";
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + invalidIndex, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_solveQuestionInvalidSolution_failure() {
        String invalidSolution = " " + PREFIX_TEXT;
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + QUESTION_INDEX_DESC + invalidSolution,
                SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);

        invalidSolution = " " + PREFIX_TEXT + " ";
        assertParseFailure(parser, SOLVE_QUESTION_DESC + "1" + QUESTION_INDEX_DESC + invalidSolution,
                SolvedQuestion.MESSAGE_SOLUTION_CONSTRAINTS);
    }

    @Test
    public void parse_solveQuestionValidArguments_success() {
        String input = SOLVE_QUESTION_DESC + "1" + SOLUTION_DESC + QUESTION_INDEX_DESC;
        SolveQuestionCommand command = new SolveQuestionCommand(INDEX_FIRST_PERSON,
                Index.fromOneBased(1), DEFAULT_SOLUTION);
        assertParseSuccess(parser, input, command);

        input = SOLVE_QUESTION_DESC + "1" + QUESTION_INDEX_DESC + SOLUTION_DESC;
        assertParseSuccess(parser, input, command);
    }

    @Test
    public void parse_deleteQuestionMissingParts_failure() {
        // missing student index
        assertParseFailure(parser, DEL_QUESTION_DESC + QUESTION_INDEX_DESC, MESSAGE_INVALID_FORMAT);

        // missing question index
        assertParseFailure(parser, DEL_QUESTION_DESC + "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_deleteQuestionInvalidPreamble_failure() {
        // invalid integers
        assertParseFailure(parser, DEL_QUESTION_DESC + "0" + QUESTION_INDEX_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, DEL_QUESTION_DESC + "-2" + QUESTION_INDEX_DESC, MESSAGE_INVALID_FORMAT);

        // invalid argument
        assertParseFailure(parser, DEL_QUESTION_DESC + "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix parsed as preamble
        String invalidPrefix = " " + PREFIX_TEXT + "string";
        assertParseFailure(parser, DEL_QUESTION_DESC + "1" + invalidPrefix, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_deleteQuestionInvalidArgument_failure() {
        String invalidQuestion = " " + PREFIX_INDEX + "-1";
        assertParseFailure(parser, DEL_QUESTION_DESC + "1" + invalidQuestion, MESSAGE_INVALID_FORMAT);

        invalidQuestion = " " + PREFIX_INDEX + "0";
        assertParseFailure(parser, DEL_QUESTION_DESC + "1" + invalidQuestion, MESSAGE_INVALID_FORMAT);

        invalidQuestion = " " + PREFIX_INDEX + " ";
        assertParseFailure(parser, DEL_QUESTION_DESC + "1" + invalidQuestion, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_deleteQuestionValidArguments_success() {
        String input = DEL_QUESTION_DESC + "1" + QUESTION_INDEX_DESC;
        DeleteQuestionCommand command = new DeleteQuestionCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1));
        assertParseSuccess(parser, input, command);
    }

}
