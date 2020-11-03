package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.notes.note.Title;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        // Empty string
        assertThrows(IllegalArgumentException.class, () -> new Title(""));

        // More than 20 characters
        assertThrows(IllegalArgumentException.class, () -> new Title("What is the meaning of life?"));

    }

    @Test
    public void isValidTitle() {
        // null name
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid name
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle("Life is very hard....")); // 21 char

        // valid name
        assertTrue(Title.isValidTitle("peter jack")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("peter the 2nd")); // alphanumeric characters
        assertTrue(Title.isValidTitle("hi!! :)")); // a few special characters
    }
}
