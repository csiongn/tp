package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.SchoolContainsKeywordsPredicate;
import seedu.address.model.student.Year;
import seedu.address.model.student.YearMatchPredicate;
import seedu.address.testutil.FindStudentDescriptorBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        FindCommand.FindStudentDescriptor descriptor =
                new FindStudentDescriptorBuilder().withNamePredicate(predicate).build();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsCompoundPredicates_returnsFindCommand() {

        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        SchoolContainsKeywordsPredicate schoolPredicate =
                new SchoolContainsKeywordsPredicate(Arrays.asList("Changi", "Sec"));
        YearMatchPredicate yearPredicate = new YearMatchPredicate(new Year("Sec 3"));

        FindCommand.FindStudentDescriptor descriptor = new FindStudentDescriptorBuilder()
                .withNamePredicate(namePredicate)
                .withSchoolPredicate(schoolPredicate)
                .withYearPredicate(yearPredicate)
                .build();

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, " n/Alice Bob s/Changi Sec y/Secondary 3", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " n/ \n Alice \n \t Bob  \n s/Changi\t Sec\t \t y/Secondary 3", expectedFindCommand);
    }

}
