package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class PaymentDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PaymentDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String[] invalidDates = new String[]{"12-1-31", "32/10/19"};
        for (String invalidDate : invalidDates) {
            assertThrows(IllegalArgumentException.class, () -> new PaymentDate(invalidDate));
        }
    }

    @Test
    public void isValidDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PaymentDate(null));
    }

    @Test
    public void isValidDate_validValue_returnsTrue() {
        assertTrue(PaymentDate.isValidDate("12/12/12"));
        assertTrue(PaymentDate.isValidDate("12/2/12"));
        assertTrue(PaymentDate.isValidDate("2/12/12"));
        assertTrue(PaymentDate.isValidDate("2/2/12"));
        assertTrue(PaymentDate.isValidDate("12/12/2002"));
        assertTrue(PaymentDate.isValidDate("12/2/2002"));
        assertTrue(PaymentDate.isValidDate("2/12/2002"));
        assertTrue(PaymentDate.isValidDate("2/2/2002"));
    }

    @Test
    public void isValidDate_invalidFormat_returnsFalse() {
        assertFalse(PaymentDate.isValidDate("")); // blank
        assertFalse(PaymentDate.isValidDate("abc")); // not even a date
        assertFalse(PaymentDate.isValidDate("2-2-22")); // - instead of / to delimit fields
        assertFalse(PaymentDate.isValidDate("12.2.31")); // . instead of / to delimit fields
        assertFalse(PaymentDate.isValidDate("2/2")); // missing at least one field

        assertFalse(PaymentDate.isValidDate("2/2/2")); // year has too few characters
        assertFalse(PaymentDate.isValidDate("2/2/020")); // year has 3 characters instead of 2/4
        assertFalse(PaymentDate.isValidDate("2/2/22020")); // year has too many characters

        assertFalse(PaymentDate.isValidDate("222/2/2")); // day has too few characters
        assertFalse(PaymentDate.isValidDate("2/222/020")); // month has 3 characters instead of 2/4
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        // invalid days
        assertFalse(PaymentDate.isValidDate("31/11/19"));
        assertFalse(PaymentDate.isValidDate("31/11/2019"));
        assertFalse(PaymentDate.isValidDate("32/10/19"));
        assertFalse(PaymentDate.isValidDate("32/10/2019"));
        assertFalse(PaymentDate.isValidDate("29/2/19"));
        assertFalse(PaymentDate.isValidDate("30/2/20"));

        assertFalse(PaymentDate.isValidDate("0/10/19")); // too low
        assertFalse(PaymentDate.isValidDate("0/10/2019"));

        // invalid months
        assertFalse(PaymentDate.isValidDate("20/0/19"));
        assertFalse(PaymentDate.isValidDate("20/13/19"));
        assertFalse(PaymentDate.isValidDate("20/0/2019"));
        assertFalse(PaymentDate.isValidDate("20/13/2019"));
    }

    @Test
    public void isValidDate_pastPresentFuture() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String future = LocalDate.now().plusDays(1).format(formatter);
        assertFalse(PaymentDate.isValidDate(future));

        String present = LocalDate.now().format(formatter);
        assertTrue(PaymentDate.isValidDate(present));

        String past = LocalDate.now().minusDays(1).format(formatter);
        assertTrue(PaymentDate.isValidDate(past));
    }
}
