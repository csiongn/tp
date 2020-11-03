package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_SCHOOL = new Prefix("s/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");
    public static final Prefix PREFIX_PAYMENT = new Prefix("d/");
    public static final Prefix PREFIX_DETAILS = new Prefix("a/");

    /* Prefix for detail and question commands */
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_TEXT = new Prefix("t/");

    /* Prefix for exam commands */
    public static final Prefix PREFIX_EXAM_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EXAM_DATE = new Prefix("d/");
    public static final Prefix PREFIX_SCORE = new Prefix("s/");
    public static final Prefix PREFIX_EXAM_INDEX = new Prefix("i/");

    /* Prefix for attendance commands */
    public static final Prefix PREFIX_ATTENDANCE_DATE = new Prefix("d/");
    public static final Prefix PREFIX_ATTENDANCE_STATUS = new Prefix("a/");
    public static final Prefix PREFIX_ATTENDANCE_FEEDBACK = new Prefix("f/");

    /* Prefix for Notebook */
    public static final Prefix PREFIX_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");

    /* Prefix for Schedule */
    public static final Prefix PREFIX_VIEW_MODE = new Prefix("m/");
    public static final Prefix PREFIX_VIEW_DATE = new Prefix("d/");

    public static final Prefix[] COMPULSORY_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE,
        PREFIX_SCHOOL, PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME};

    public static final Prefix[] ALL_NOTE_PREFIXES = new Prefix[] {PREFIX_TITLE, PREFIX_DESCRIPTION};

    public static final Prefix[] ALL_PREFIXES = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_SCHOOL,
        PREFIX_YEAR, PREFIX_VENUE, PREFIX_TIME, PREFIX_FEE, PREFIX_PAYMENT, PREFIX_DETAILS};

    public static final Prefix[] FIND_SUPPORTED_PREFIXES =
            new Prefix[] {PREFIX_NAME, PREFIX_SCHOOL, PREFIX_YEAR};

    public static final Prefix[] COMMAND_PREFIXES =
            new Prefix[] {PREFIX_INDEX, PREFIX_TEXT};

    public static final Prefix[] EXAM_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_EXAM_NAME, PREFIX_EXAM_DATE, PREFIX_SCORE};

    public static final Prefix[] ATTENDANCE_COMMAND_PREFIXES =
            new Prefix[] {PREFIX_ATTENDANCE_DATE, PREFIX_ATTENDANCE_STATUS, PREFIX_ATTENDANCE_FEEDBACK};

}
