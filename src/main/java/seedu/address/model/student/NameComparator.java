package seedu.address.model.student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().fullName.compareTo(o2.getName().fullName);
    }
}