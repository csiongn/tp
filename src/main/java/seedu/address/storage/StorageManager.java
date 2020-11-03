package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.storage.notes.NotebookStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ReeveStorage reeveStorage;
    private NotebookStorage notebookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ReeveStorage reeveStorage, UserPrefsStorage userPrefsStorage,
                          NotebookStorage notebookStorage) {
        super();
        this.reeveStorage = reeveStorage;
        this.notebookStorage = notebookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return reeveStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyReeve> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(reeveStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyReeve> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return reeveStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyReeve reeve) throws IOException {
        saveAddressBook(reeve, reeveStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyReeve reeve, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        reeveStorage.saveAddressBook(reeve, filePath);
    }

    // ================ Notebook methods ==============================

    @Override
    public Path getNotebookFilePath() {
        return notebookStorage.getNotebookFilePath();
    }

    @Override
    public Optional<ReadOnlyNotebook> readNotebook() throws DataConversionException, IOException {
        return readNotebook(notebookStorage.getNotebookFilePath());
    }

    @Override
    public Optional<ReadOnlyNotebook> readNotebook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from notebook file: " + filePath);
        return notebookStorage.readNotebook(filePath);
    }

    @Override
    public void saveNotebook(ReadOnlyNotebook notebook) throws IOException {
        saveNotebook(notebook, notebookStorage.getNotebookFilePath());
    }

    @Override
    public void saveNotebook(ReadOnlyNotebook notebook, Path filePath) throws IOException {
        logger.fine("Attempting to write to notebook data file: " + filePath);
        notebookStorage.saveNotebook(notebook, filePath);
    }

}
