package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FEEDBACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FEEDBACK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_VENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_SCORE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Reeve;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.academic.exam.Score;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withSchool("Anderson Secondary").withYear("Sec 3").withPhone("94351253")
            .withClassVenue("123, Jurong West Ave 6, #08-111")
            .withClassTime("5 1500-1700")
            .withDetails("owesMoney", "friends")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withSchool("Pei Hwa Secondary").withYear("Sec 2").withPhone("98765432")
            .withClassTime("2 1230-1430")
            .withExams(new Exam("CA2", "19/9/2020", new Score("73/100")),
                    new Exam("CA1", "2/3/2020", new Score("21/40")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withSchool("Catholic High").withYear("Sec 5").withPhone("95352563")
            .withFee("450.50")
            .withClassTime("1 1500-1700")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withSchool("Methodist Girls School").withYear("Sec 1").withPhone("87652533")
            .withPaymentDate("28/2/2018").withClassTime("1 1700-1900")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withSchool("River Valley High").withYear("JC 2").withPhone("9482224")
            .withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_NEWTON, DEFAULT_QUESTION_MATH).withClassTime("1 1900-2100")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withSchool("Raffles Girls School").withYear("Sec 2")
            .withClassTime("3 1200-1430")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withSchool("Montford Secondary").withYear("Sec 4")
            .withClassTime("2 1000-1130")
            .withExams(new Exam("End of Year Examination 2020", "7/11/2020", new Score("50/100")),
                    new Exam("Mid Year Examination 2020", "25/7/2020", new Score("20/30")))
            .withAttendances(new Attendance("14/04/1998", "present",
                    new Feedback("sleepy during lesson")))
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withSchool("NUS High School").withYear("JC 2").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withSchool("NUS High School").withYear("JC 2").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withSchool(VALID_SCHOOL_AMY)
            .withYear(VALID_YEAR_AMY)
            .withClassVenue(VALID_CLASS_VENUE_AMY)
            .withClassTime(VALID_CLASS_TIME_AMY)
            .withFee(VALID_FEE_AMY)
            .withPaymentDate(VALID_PAYMENT_DATE_AMY)
            .withDetails(VALID_ADDITIONAL_DETAILS_AMY)
            .withQuestions(VALID_QUESTION_AMY)
            .withExams(new Exam(VALID_EXAM_NAME_AMY, VALID_EXAM_DATE_AMY, new Score(VALID_EXAM_SCORE_AMY)))
            .withAttendances(new Attendance(VALID_ATTENDANCE_DATE_AMY, VALID_ATTENDANCE_STATUS_AMY,
                    new Feedback(VALID_ATTENDANCE_FEEDBACK_AMY)))
            .build();

    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withSchool(VALID_SCHOOL_BOB)
            .withYear(VALID_YEAR_BOB)
            .withClassVenue(VALID_CLASS_VENUE_BOB)
            .withClassTime(VALID_CLASS_TIME_BOB)
            .withFee(VALID_FEE_BOB)
            .withPaymentDate(VALID_PAYMENT_DATE_BOB)
            .withDetails(VALID_ADDITIONAL_DETAILS_BOB)
            .withSolved(DEFAULT_SOLUTION, VALID_QUESTION_BOB)
            .withExams(new Exam(VALID_EXAM_NAME_BOB, VALID_EXAM_DATE_BOB, new Score(VALID_EXAM_SCORE_BOB)))
            .withAttendances(new Attendance(VALID_ATTENDANCE_DATE_BOB, VALID_ATTENDANCE_STATUS_BOB,
                    new Feedback(VALID_ATTENDANCE_FEEDBACK_BOB)))
            .build();

    private TypicalStudents() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static Reeve getTypicalAddressBook() {
        Reeve ab = new Reeve();
        for (Student student : getTypicalPersons()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
