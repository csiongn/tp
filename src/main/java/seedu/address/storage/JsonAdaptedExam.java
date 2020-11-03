package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;


/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExam {

    public static final String MISSING_EXAM_FIELD_MESSAGE_FORMAT = "Exam's %s field is missing!";

    private final String examName;
    private final String examDate;
    private final String score;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code exam}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("examName") String examName,
                           @JsonProperty("examDate") String examDate,
                           @JsonProperty("score") String score) {
        this.examName = examName;
        this.examDate = examDate;
        this.score = score;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        this.examName = source.getName();
        this.examDate = source.getUserInputDate();
        this.score = source.getScore().toString();
    }

    /**
     * Converts this Jackson-friendly adapted exam object into the model's {@code Exam} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Exam toModelType() throws IllegalValueException {
        if (examName == null || examName.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_EXAM_FIELD_MESSAGE_FORMAT,
                    "Exam Name"));
        }

        if (examDate == null || examDate.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_EXAM_FIELD_MESSAGE_FORMAT,
                    "Exam Date"));
        }

        if (!Exam.isValidDate(examDate)) {
            throw new IllegalValueException("Invalid date format");
        }

        if (!Score.isValidExamScore(score)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Exam(examName, examDate, new Score(score));
    }
}
