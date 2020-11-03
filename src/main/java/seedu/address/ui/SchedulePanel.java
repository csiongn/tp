package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.icalendarfx.components.VEvent;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the schedule.
 */
public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";
    private static final Locale UK = Locale.UK;
    private final VCalendar vCalendar;
    private final ICalendarAgenda agenda;
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private BorderPane borderPane;

    /**
     * Creates a {@code UiPart<Region>} wrapping an ICalendar.
     * This is to be shown on the main panel of the app.
     * @param list list of VEvents that are to displayed.
     */
    public SchedulePanel(ObservableList<VEvent> list) {
        super(FXML);
        this.vCalendar = new VCalendar();
        vCalendar.setVEvents(list);
        this.agenda = new ICalendarAgenda(this.vCalendar);
        init(this.agenda);
        borderPane.setStyle("-fx-background-color: #ffffff;");
        borderPane.setCenter(agenda);
    }

    /**
     * Initialises the schedule panel. Default view is the weekly view.
     */
    public void init(ICalendarAgenda agenda) {
        agenda.setLocale(UK);
        setWeekView();
        disableUserGraphicsForICalendar(this.agenda);
    }

    /**
     * Disables the graphical feature of iCalendar.
     * @param agenda the settings for the iCalendar.
     */
    public void disableUserGraphicsForICalendar(ICalendarAgenda agenda) {
        agenda.setAllowResize(false);
        agenda.setAllowDragging(false);
        agenda.setOnMousePressed(null);
        agenda.setOnTouchPressed(null);
        agenda.setEditAppointmentCallback(null);
        agenda.setAppointmentChangedCallback(null);
        agenda.setNewAppointmentCallback(null);
        agenda.setNewAppointmentDrawnCallback(null);
        agenda.setOnMouseClicked(null);
        agenda.setSelectedOneAppointmentCallback(param -> null);
    }

    /**
     * Sets the iCalendar to weekly view.
     */
    public void setWeekView() {
        AgendaWeekSkin weekSkin = new AgendaWeekSkin(this.agenda);
        agenda.setSkin(weekSkin);
    }
    /**
     * Sets the iCalendar to a daily view.
     */
    public void setDayView() {
        AgendaDaySkin dailySkin = new AgendaDaySkin(this.agenda);
        agenda.setSkin(dailySkin);
    }

    /**
     * Changes panel to show time interval.
     * @param localDateTime local date and time.
     */
    public void setDisplayedDateTime(LocalDateTime localDateTime) {
        this.agenda.setDisplayedLocalDateTime(localDateTime);
    }

    /**
     * Updates the iCalendarAgenda to propagate changes to internal VEvents.
     */
    public void updateSchedule(List<VEvent> newData) {
        this.vCalendar.setVEvents(newData);
    }

}
