package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's monthly tuition fees.
 * Guarantees: immutable; is valid as declared in
 */
public class Fee {

    public static final String MESSAGE_CONSTRAINTS =
            "Fees should strictly contain only digits up to 2 decimal points, and be in SGD";

    public static final String VALIDATION_REGEX = "[0-9]+((\\.[0-9]{1,2})?)";

    public static final String FREE_OF_CHARGE = "0";

    public final double amount;

    /**
     * Constructs a {@code Fee}.
     */
    public Fee(String amount) {
        requireNonNull(amount);
        checkArgument(isValidFee(amount), MESSAGE_CONSTRAINTS);
        this.amount = Double.parseDouble(amount);
    }

    /**
     * Returns true if the given string is in the correct format.
     */
    public static boolean isValidFee(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts a Fee object to a the original user input string
     */
    public String convertFeeToUserInputString() {
        return String.valueOf(amount);
    }

    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj)
                || (obj instanceof Fee)
                && amount == ((Fee) obj).amount;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(amount);
    }

}
