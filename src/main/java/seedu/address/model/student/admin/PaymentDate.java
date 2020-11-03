package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the last time a Student paid his tuition fees.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class PaymentDate {

    public static final String MESSAGE_CONSTRAINTS = "Payment dates should be a valid date in the form dd/mm/yy, "
            + "should not be blank, "
            + "and should not be future-dated";

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static final String TODAY = LocalDate.now().format(INPUT_DEF);

    private static final Pattern VALIDATION_REGEX =
            Pattern.compile("(?<day>[0-9]{1,2})(/)(?<month>[0-9]{1,2})(/)(?<year>[0-9]{2}|[0-9]{4})");

    public final LocalDate lastPaid;

    /**
     * Constructs a {@code PaymentDate}.
     *
     * @param lastPaid A valid payment date.
     */
    public PaymentDate(String lastPaid) {
        requireNonNull(lastPaid);
        checkArgument(isValidDate(lastPaid), MESSAGE_CONSTRAINTS);
        this.lastPaid = parseToDate(lastPaid);
    }

    /*
     * The {@code String} has already been validated by {@link #isValidDate(String)}.
     * We just have to find out which format it fits.
     */
    private LocalDate parseToDate(String lastPaid) {
        try {
            return LocalDate.parse(lastPaid, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            // date is in d/M/yyyy
            return LocalDate.parse(lastPaid, INPUT_ALT);
        }
    }

    /**
     * Returns true if a given string is in the correct date format.
     */
    public static boolean isValidDate(String test) {
        Matcher matcher = VALIDATION_REGEX.matcher(test);
        if (!matcher.matches()) {
            return false;
        }

        String day = matcher.group("day");
        String month = matcher.group("month");
        String year = matcher.group("year");

        if (year.length() < 4) {
            year = "20" + year;
        }

        int parseDay = Integer.parseInt(day);
        int parseMonth = Integer.parseInt(month);
        int parseYear = Integer.parseInt(year);

        try {
            LocalDate date = LocalDate.of(parseYear, parseMonth, parseDay);
            return date.compareTo(LocalDate.now()) <= 0;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public String convertPaymentDateToUserInputString() {
        return this.lastPaid.format(INPUT_ALT);
    }

    @Override
    public String toString() {
        return lastPaid.format(OUTPUT);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) // short circuit if same object
                || (obj instanceof PaymentDate) // instanceof handles nulls
                && lastPaid.equals(((PaymentDate) obj).lastPaid); // state check
    }

    @Override
    public int hashCode() {
        return lastPaid.hashCode();
    }

}
