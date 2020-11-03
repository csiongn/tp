package seedu.address.model.student.academic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Feedback {

    public static final String MESSAGE_CONSTRAINTS = "Feedback should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[\\s]*[a-zA-Z0-9][a-zA-Z0-9\\s]*$";

    private String feedback;

    /**
     * Constructs a {@code Feedback} object.
     * @param feedback feedback for student.
     */
    public Feedback(String feedback) {
        requireNonNull(feedback);
        checkArgument(isValidFeedback(feedback), MESSAGE_CONSTRAINTS);

        this.feedback = feedback;
    }

    /**
     * Returns true if a given string is a valid feedback.
     */
    public static boolean isValidFeedback(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.feedback;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Feedback)) {
            return false;
        }

        Feedback other = (Feedback) obj;
        return other.feedback.equals(feedback);
    }
}
