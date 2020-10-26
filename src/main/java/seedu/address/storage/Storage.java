package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.storage.notes.NotebookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends ReeveStorage, NotebookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyReeve> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyReeve addressBook) throws IOException;

    @Override
    Optional<ReadOnlyNotebook> readNotebook() throws DataConversionException, IOException;

    @Override
    void saveNotebook(ReadOnlyNotebook notebook) throws IOException;

}
