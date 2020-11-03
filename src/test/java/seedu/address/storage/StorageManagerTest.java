package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.storage.notes.JsonNotebookStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonReeveStorage addressBookStorage = new JsonReeveStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonNotebookStorage notebookStorage = new JsonNotebookStorage(getTempFilePath("notebook"));

        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, notebookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        Reeve original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyReeve retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new Reeve(retrieved));
    }

    @Test
    public void notebookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonNotebookStorage} class.
         */
        Notebook original = getTypicalNotebook();
        storageManager.saveNotebook(original);
        ReadOnlyNotebook retrieved = storageManager.readNotebook().get();
        assertEquals(original, new Notebook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void getNotebookFilePath() {
        assertNotNull(storageManager.getNotebookFilePath());
    }

}
