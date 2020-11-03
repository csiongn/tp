package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

public class YearComparator implements Comparator<Student> {

    public static final String COMPARISON_MEANS = "year";
    public static final String USAGE = COMPARISON_MEANS + ": Sorts students by the year they are in school, "
            + "with Primary 1 coming first and JC 2 coming last.";

    @Override
    public int compare(Student o1, Student o2) {
        requireNonNull(o1);
        requireNonNull(o2);
        Year year1 = o1.getYear();
        Year year2 = o2.getYear();
        assert year1 != null;
        assert year2 != null;
        if (year1.schoolType.compareTo(year2.schoolType) == 0) {
            return year1.level.compareTo(year2.level);
        } else {
            return year1.schoolType.compareTo(year2.schoolType);
        }
    }
}
