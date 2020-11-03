package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

public class ExamStatsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ExamStatsWindow.class);
    private static final String FXML = "ExamStatsWindow.fxml";
    private static final String lineChartTitle = "Exam Statistics of ";

    @FXML
    private LineChart<String, Number> lineChart;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ExamStatsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ExamStatsWindow(Student student) {
        this(new Stage());
        lineChart.setTitle(lineChartTitle + student.getName().toString());
        fillLineChart(student);
    }

    /**
     * Shows the exam stats window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing exam stats page of student.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    private void fillLineChart(Student student) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Exam Score");
        List<Exam> exams = student.getExams();
        exams.sort(new Comparator<Exam>() {
            @Override
            public int compare(Exam exam1, Exam exam2) {
                return exam1.getDate().compareTo(exam2.getDate());
            }
        });
        for (Exam exam : exams) {
            series.getData().add(new XYChart.Data<>(exam.getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    exam.getScore().getScorePercentage()));
        }
        lineChart.getData().add(series);
    }
}
