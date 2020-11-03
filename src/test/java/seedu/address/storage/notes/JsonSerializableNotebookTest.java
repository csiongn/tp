package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.notes.Notebook;
import seedu.address.testutil.notes.TypicalNotes;

public class JsonSerializableNotebookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNotebookTest");
    private static final Path TYPICAL_NOTES_FILE = TEST_DATA_FOLDER.resolve("typicalNotesNotebook.json");
    private static final Path INVALID_NOTES_FILE = TEST_DATA_FOLDER.resolve("invalidNoteNotebook.json");
    private static final Path DUPLICATE_NOTES_FILE = TEST_DATA_FOLDER.resolve("duplicateNoteNotebook.json");

    @Test
    public void toModelType_typicalNotesFile_success() throws Exception {
        JsonSerializableNotebook dataFromFile = JsonUtil.readJsonFile(TYPICAL_NOTES_FILE,
                JsonSerializableNotebook.class).get();
        Notebook notebookFromFile = dataFromFile.toModelType();
        Notebook typicalNotesReeve = TypicalNotes.getTypicalNotebook();
        assertEquals(notebookFromFile, typicalNotesReeve);
    }

    @Test
    public void toModelType_invalidNotesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNotebook dataFromFile = JsonUtil.readJsonFile(INVALID_NOTES_FILE,
                JsonSerializableNotebook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateNotes_throwsIllegalValueException() throws Exception {
        JsonSerializableNotebook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_NOTES_FILE,
                JsonSerializableNotebook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNotebook.MESSAGE_DUPLICATE_NOTE,
                dataFromFile::toModelType);
    }

}
