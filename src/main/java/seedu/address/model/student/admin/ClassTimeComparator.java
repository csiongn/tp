package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.student.Student;

public class ClassTimeComparator implements Comparator<Student> {

    public static final String COMPARISON_MEANS = "classTime";
    public static final String USAGE = COMPARISON_MEANS + ": Sorts students by the day followed "
            + "by time of their class";

    @Override
    public int compare(Student o1, Student o2) {
        requireNonNull(o1);
        requireNonNull(o2);
        ClassTime classTime1 = o1.getAdmin().getClassTime();
        ClassTime classTime2 = o2.getAdmin().getClassTime();
        assert classTime1 != null;
        assert classTime2 != null;
        if (classTime1.dayOfWeek.compareTo(classTime2.dayOfWeek) == 0) {
            return classTime1.startTime.compareTo(classTime2.startTime);
        } else {
            return classTime1.dayOfWeek.compareTo(classTime2.dayOfWeek);
        }
    }
}
