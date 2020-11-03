package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.notes.note.Description;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        // Empty string
        assertThrows(IllegalArgumentException.class, () -> new Description(""));

        // More than 80 characters
        assertThrows(IllegalArgumentException.class, () -> new Description("Lorem ipsum dolor"
                + " sit amet, consectetur adipiscing elit. Integer dapibus sapienar"));

    }

    @Test
    public void isValidDescription() {
        // EP: null
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // EP: empty spaces
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // EP: 0 characters
        assertFalse(Description.isValidDescription("")); // empty string

        // EP: 1 char
        assertTrue(Description.isValidDescription("p")); // alphabets only

        // EP: 80 char
        assertTrue(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetur"
                + " adipiscing elit. Integer dapibus sapiena")); // 80 char

        // EP: 81 char
        assertFalse(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetur"
                + " adipiscing elit. Integer dapibus sapienar")); // 80 char

        // EP: special characters
        assertTrue(Description.isValidDescription("hi!! :)")); // a few special characters
    }
}
