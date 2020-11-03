package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {

    public static final String COMPARISON_MEANS = "name";
    public static final String USAGE = COMPARISON_MEANS + ": Sorts students by their name (case insensitive)";

    @Override
    public int compare(Student o1, Student o2) {
        requireNonNull(o1);
        requireNonNull(o2);
        return o1.getName().fullName.toLowerCase().compareTo(o2.getName().fullName.toLowerCase());
    }
}
