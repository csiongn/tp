package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ReeveParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.schedule.ScheduleViewMode;
import seedu.address.model.student.Student;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ReeveParser reeveParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        reeveParser = new ReeveParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = reeveParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getReeve());
            storage.saveNotebook(model.getNotebook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyReeve getAddressBook() {
        return model.getReeve();
    }

    @Override
    public ReadOnlyNotebook getNotebook() {
        return model.getNotebook();
    }

    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return model.getSortedStudentList();
    }

    @Override
    public ObservableList<Student> getSortedStudentList() {
        return model.getSortedStudentList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public LocalDateTime getScheduleViewDateTime() {
        return model.getScheduleViewDateTime();
    }

    @Override
    public ScheduleViewMode getScheduleViewMode() {
        return model.getScheduleViewMode();
    }

    @Override
    public ObservableList<VEvent> getVEventList() {
        return model.getLessonEventsList();
    }
}
