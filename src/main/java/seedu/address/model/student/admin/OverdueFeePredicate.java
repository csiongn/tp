package seedu.address.model.student.admin;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code PaymentDate} is more than a month earlier than the current date.
 */
public class OverdueFeePredicate implements Predicate<Student> {

    private final LocalDate currentDate;

    public OverdueFeePredicate() {
        currentDate = LocalDate.now();
    }

    @Override
    public boolean test(Student student) {
        return currentDate.minusMonths(1).compareTo(student.getPaymentDate().lastPaid) > 0
                && student.getAdmin().getFee().amount > 0;
    }
}
