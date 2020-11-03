package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_BUY_COFFEE;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_EVENTS;
import static seedu.address.testutil.notes.TypicalNotes.NOTE_TODO;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;

public class JsonNotebookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNotebookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNotebook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNotebook(null));
    }

    private java.util.Optional<ReadOnlyNotebook> readNotebook(String filePath) throws Exception {
        return new JsonNotebookStorage(Paths.get(filePath)).readNotebook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNotebook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readNotebook("notJsonFormatNotebook.json"));
    }

    @Test
    public void readNotebook_invalidNoteNotebook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNotebook("invalidNoteNotebook.json"));
    }

    @Test
    public void readNotebook_invalidAndValidNoteNotebook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNotebook("invalidAndValidNoteNotebook.json"));
    }

    @Test
    public void readAndSaveNotebook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNotebook.json");
        Notebook original = getTypicalNotebook();
        JsonNotebookStorage jsonNotebookStorage = new JsonNotebookStorage(filePath);

        // Save in new file and read back
        jsonNotebookStorage.saveNotebook(original, filePath);
        ReadOnlyNotebook readBack = jsonNotebookStorage.readNotebook(filePath).get();
        assertEquals(original, new Notebook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addNote(NOTE_TODO);
        original.removeNote(NOTE_BUY_COFFEE);
        jsonNotebookStorage.saveNotebook(original, filePath);
        readBack = jsonNotebookStorage.readNotebook(filePath).get();
        assertEquals(original, new Notebook(readBack));

        // Save and read without specifying file path
        original.addNote(NOTE_EVENTS);
        jsonNotebookStorage.saveNotebook(original); // file path not specified
        readBack = jsonNotebookStorage.readNotebook().get(); // file path not specified
        assertEquals(original, new Notebook(readBack));

    }

    @Test
    public void saveNotebook_nullNotebook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotebook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code notebook} at the specified {@code filePath}.
     */
    private void saveNotebook(ReadOnlyNotebook notebook, String filePath) {
        try {
            new JsonNotebookStorage(Paths.get(filePath))
                    .saveNotebook(notebook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNotebook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotebook(new Notebook(), null));
    }
}
