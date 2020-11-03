package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubAcceptingNoteAdded;
import seedu.address.model.ModelStubWithNote;
import seedu.address.model.notes.note.Note;
import seedu.address.testutil.notes.NoteBuilder;

public class AddNoteCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute(modelStub);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validNote), modelStub.notesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Note validNote = new NoteBuilder().build();
        AddNoteCommand addNoteCommand = new AddNoteCommand(validNote);
        ModelStub modelStub = new ModelStubWithNote(validNote);

        assertThrows(CommandException.class,
                AddNoteCommand.MESSAGE_DUPLICATE_NOTE, () -> addNoteCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Note thingsToDo = new NoteBuilder().withTitle("things to do").withDescription("finish marking").build();
        Note events = new NoteBuilder().withTitle("things to do").withDescription("meet benedict").build();
        Note friends = new NoteBuilder().withTitle("meeting").withDescription("meet benedict").build();

        AddNoteCommand addThingsToDoCommand = new AddNoteCommand(thingsToDo);
        AddNoteCommand addEventsCommand = new AddNoteCommand(events);
        AddNoteCommand addMeetingFriendsCommand = new AddNoteCommand(friends);

        // same object -> returns true
        assertEquals(addEventsCommand, addEventsCommand);

        // same values -> returns true
        AddNoteCommand addThingsToDoCommandCopy = new AddNoteCommand(thingsToDo);
        assertEquals(addThingsToDoCommandCopy, addThingsToDoCommand);

        // different types -> returns false
        assertNotEquals(addThingsToDoCommand, 1);

        // null -> returns false
        assertNotEquals(addThingsToDoCommand, null);

        // different title -> returns true
        assertNotEquals(addThingsToDoCommand, addMeetingFriendsCommand);

        // different description -> returns false
        assertNotEquals(addEventsCommand, addThingsToDoCommand);
    }

}
