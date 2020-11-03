package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OverdueCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.notes.AddNoteCommand;
import seedu.address.logic.commands.notes.DeleteNoteCommand;
import seedu.address.logic.commands.notes.EditNoteCommand;
import seedu.address.logic.commands.notes.NoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.note.Note;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.question.UnsolvedQuestion;
import seedu.address.testutil.EditAdminDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.FindStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;
import seedu.address.testutil.notes.EditNoteDescriptorBuilder;
import seedu.address.testutil.notes.NoteBuilder;
import seedu.address.testutil.notes.NoteUtil;

public class ReeveParserTest {

    private final ReeveParser parser = new ReeveParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().withQuestions().withDetails().withExams().withAttendances().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand.EditAdminDescriptor editAdminDescriptor = new EditAdminDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                + StudentUtil.getEditStudentDescriptorDetails(editStudentDescriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, editStudentDescriptor, editAdminDescriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywords);
        FindCommand.FindStudentDescriptor descriptor = new FindStudentDescriptorBuilder()
                .withNamePredicate(namePredicate).build();
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + StudentUtil.getFindStudentDescriptorDetails(descriptor));
        assertEquals(new FindCommand(descriptor), command);
    }

    @Test
    public void parseCommand_question() throws Exception {
        String addCommandDesc = QuestionCommand.COMMAND_WORD + " " + AddQuestionCommand.COMMAND_WORD + " ";
        String questionDesc = " " + PREFIX_TEXT + DEFAULT_QUESTION_MATH;
        UnsolvedQuestion question = new UnsolvedQuestion(DEFAULT_QUESTION_MATH);
        QuestionCommand command = (QuestionCommand) parser.parseCommand(addCommandDesc + "1" + questionDesc);
        assertEquals(new AddQuestionCommand(INDEX_FIRST_PERSON, question), command);

        String solveCommandDesc = QuestionCommand.COMMAND_WORD + " " + SolveQuestionCommand.COMMAND_WORD + " ";
        questionDesc = " " + PREFIX_INDEX + "1" + " " + PREFIX_TEXT + DEFAULT_SOLUTION;
        command = (QuestionCommand) parser.parseCommand(solveCommandDesc + "1" + questionDesc);
        assertEquals(new SolveQuestionCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1), DEFAULT_SOLUTION), command);

        String delCommandDesc = QuestionCommand.COMMAND_WORD + " " + DeleteQuestionCommand.COMMAND_WORD + " ";
        questionDesc = " " + PREFIX_INDEX + "1";
        command = (QuestionCommand) parser.parseCommand(delCommandDesc + "1" + questionDesc);
        assertEquals(new DeleteQuestionCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_exam() throws Exception {
        assertTrue(parser.parseCommand("exam add 1 n/Mid Year 2020 d/23/7/2020 s/50/100") instanceof AddExamCommand);
        assertTrue(parser.parseCommand("exam delete 1 i/1") instanceof DeleteExamCommand);

    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " classTime") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " year") instanceof SortCommand);
    }

    @Test
    public void parseCommand_detail() throws Exception {
        assertTrue(parser.parseCommand("detail add 2 t/ smart") instanceof DetailCommand);
    }

    @Test
    public void parseCommand_unpaid() throws Exception {
        assertTrue(parser.parseCommand(OverdueCommand.COMMAND_WORD) instanceof OverdueCommand);
        assertTrue(parser.parseCommand(OverdueCommand.COMMAND_WORD + " 3") instanceof OverdueCommand);
    }

    @Test
    public void parseCommand_note() throws Exception {
        // add notes command
        assertTrue(parser.parseCommand(NoteCommand.COMMAND_WORD + " " + AddNoteCommand.COMMAND_WORD
                + " " + "t/hi d/hehe") instanceof NoteCommand);

        // edit notes command
        Note note = new NoteBuilder().build();
        EditNoteCommand.EditNoteDescriptor editNoteDescriptor =
                new EditNoteDescriptorBuilder(note).build();
        EditNoteCommand command = (EditNoteCommand) parser.parseCommand(
                NoteCommand.COMMAND_WORD + " " + EditNoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + NoteUtil.getEditNoteDescriptorDetails(editNoteDescriptor));
        assertEquals(new EditNoteCommand(INDEX_FIRST, editNoteDescriptor), command);

        // delete notes command
        DeleteNoteCommand deleteNoteCommand = (DeleteNoteCommand) parser.parseCommand(
                NoteCommand.COMMAND_WORD + " " + DeleteNoteCommand.COMMAND_WORD
                        + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteNoteCommand(INDEX_FIRST), deleteNoteCommand);
    }



    @Test
    public void parseCommand_attendance() throws Exception {
        assertTrue(parser.parseCommand("attendance add 1 d/19/02/2020 a/present f/attentive")
                instanceof AttendanceCommand);
        assertTrue(parser.parseCommand("attendance delete 3 d/25/12/2020")
                instanceof AttendanceCommand);
    }
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
