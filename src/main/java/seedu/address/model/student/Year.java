package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year should contain the type of school and a number representing the level. The type "
            + "of school can only be one of \"secondary\", \"Secondary\", \"sec\", \"Sec\", \"s\", \"S\" "
            + "\"primary\", \"Primary\", \"pri\", \"Pri\", \"p\", \"P\", \"j\", \"J\", \"JC\", \"Jc\", \"jc\""
            + " and the level must be valid for the type of school.";

    public static final String VALIDATION_REGEX = "((secondary|sec|s|Secondary|Sec|S)\\s*([1-5])|"
        + "(primary|pri|p|Primary|Pri|P)\\s*([1-6])|(jc|Jc|j|J|JC)\\s*([1-2]))\\s*";

    /**
     * Used for separation of year into school type and level.
     */
    private static final Pattern YEAR_FORMAT = Pattern.compile("(?<school>[^\\d\\s]+)\\s*(?<level>\\d)");

    public final SchoolType schoolType;
    public final Integer level;

    /**
     * Constructs a {@code Year}.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);

        final Matcher matcher = getMatcher(year);
        String schoolTypeString = matcher.group("school").trim().toLowerCase();
        String levelString = matcher.group("level").trim().toLowerCase();

        this.schoolType = parseSchoolType(schoolTypeString);
        this.level = Integer.parseInt(levelString);
    }

    private static Matcher getMatcher(String year) {
        Matcher matcher = YEAR_FORMAT.matcher(year);
        boolean matches = matcher.matches();
        assert matches; // this should only be reached if the input matches the regex in the first place
        return matcher;
    }

    private static SchoolType parseSchoolType(String schoolTypeString) {
        assert SchoolType.isValidSchoolType(schoolTypeString); // if it passes Year regex it should pass SchoolType's
        return SchoolType.LOOKUP_TABLE.get(schoolTypeString);
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.schoolType, this.level);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && schoolType.equals(((Year) other).schoolType)) // state check
                && level.equals(((Year) other).level);
    }
}
