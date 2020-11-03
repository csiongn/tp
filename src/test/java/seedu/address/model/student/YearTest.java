package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void parseSchoolType_primary() {
        String primary = "Primary 1";
        assertEquals(new Year(primary).schoolType, SchoolType.PRIMARY);

        primary = "P 1";
        assertEquals(new Year(primary).schoolType, SchoolType.PRIMARY);

        primary = "Pri 1";
        assertEquals(new Year(primary).schoolType, SchoolType.PRIMARY);
    }

    @Test
    public void parseSchoolType_secondary() {
        String secondary = "Secondary 1";
        assertEquals(new Year(secondary).schoolType, SchoolType.SECONDARY);

        secondary = "Sec 1";
        assertEquals(new Year(secondary).schoolType, SchoolType.SECONDARY);

        secondary = "S 1";
        assertEquals(new Year(secondary).schoolType, SchoolType.SECONDARY);
    }

    @Test
    public void parseSchoolType_juniorCollege() {
        String juniorCollege = "JC 1";
        assertEquals(new Year(juniorCollege).schoolType, SchoolType.JC);

        juniorCollege = "J 1";
        assertEquals(new Year(juniorCollege).schoolType, SchoolType.JC);

        juniorCollege = "Jc 1";
        assertEquals(new Year(juniorCollege).schoolType, SchoolType.JC);
    }

    @Test
    public void isValidYear_invalidFormat() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid input
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // space only
        assertFalse(Year.isValidYear(" Primary 2")); //spacing in front
        assertFalse(Year.isValidYear("!@#$")); // characters
        assertFalse(Year.isValidYear("NS 1")); // not a valid school type
        assertFalse(Year.isValidYear("j")); // not a valid level
        assertFalse(Year.isValidYear("0")); // just digits
        assertFalse(Year.isValidYear("11")); // just digits
        assertFalse(Year.isValidYear("92312432423")); // long number
        assertFalse(Year.isValidYear("1 2 3 ")); //numbers with spacing
        assertFalse(Year.isValidYear("abc")); // only letters
        assertFalse(Year.isValidYear("a b c ")); //letters with spacing
    }

    @Test
    public void isValidYear_primaryLevels() {
        // level
        assertFalse(Year.isValidYear("Primary 0")); // lower than min value
        assertFalse(Year.isValidYear("Primary 7")); // higher than max value
        assertTrue(Year.isValidYear("Primary 1")); // min value
        assertTrue(Year.isValidYear("Primary 6")); // max value

        // school type string
        assertTrue(Year.isValidYear("P 2"));
        assertTrue(Year.isValidYear("Pri 2"));
        assertTrue(Year.isValidYear("primary 2")); // lower case
        assertTrue(Year.isValidYear("p 2")); // lower case
        assertTrue(Year.isValidYear("pri 2")); // lower case
        assertTrue(Year.isValidYear("Primary    2")); // space between values
        assertTrue(Year.isValidYear("Primary2")); // no space between values
    }

    @Test
    public void isValidYear_secondaryLevels() {
        // level
        assertFalse(Year.isValidYear("Secondary 0")); // lower than min value
        assertFalse(Year.isValidYear("Secondary 6")); // higher than max value
        assertTrue(Year.isValidYear("Secondary 1")); // min value
        assertTrue(Year.isValidYear("Secondary 5")); // max value

        // school type string
        assertTrue(Year.isValidYear("S 2"));
        assertTrue(Year.isValidYear("Sec 2"));
        assertTrue(Year.isValidYear("secondary 2")); // lower case
        assertTrue(Year.isValidYear("s 2")); // lower case
        assertTrue(Year.isValidYear("sec 2")); // lower case
        assertTrue(Year.isValidYear("Secondary    2")); // space between values
        assertTrue(Year.isValidYear("Secondary2")); // no space between values
    }

    @Test
    public void isValidYear_juniorCollegeLevels() {
        // level
        assertFalse(Year.isValidYear("JC 0")); // lower than min value
        assertFalse(Year.isValidYear("JC 3")); // higher than max value
        assertTrue(Year.isValidYear("JC 1")); // min value
        assertTrue(Year.isValidYear("JC 2")); // max value

        // school type string
        assertTrue(Year.isValidYear("J 2"));
        assertTrue(Year.isValidYear("Jc 2"));
        assertTrue(Year.isValidYear("jc 2")); // lower case
        assertTrue(Year.isValidYear("j 2")); // lower case
        assertTrue(Year.isValidYear("JC    2")); // space between values
        assertTrue(Year.isValidYear("JC2")); // no space between values
    }
}
