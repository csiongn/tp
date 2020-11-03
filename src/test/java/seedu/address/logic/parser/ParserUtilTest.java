package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Title;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Feedback;
import seedu.address.model.student.academic.exam.Score;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Detail;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SCHOOL = " ";
    private static final String INVALID_YEAR = "$4";
    private static final String INVALID_CLASS_VENUE = " ";
    private static final String INVALID_CLASS_TIME = "8 1240-2400";
    private static final String INVALID_FEE = "231.451";
    private static final String INVALID_PAYMENT_DATE = "23-9-2019";
    private static final String INVALID_ADDITIONAL_DETAIL = "sch!zophren#c";
    private static final String INVALID_QUESTION = "";
    private static final String INVALID_SOLUTION = " ";
    private static final String INVALID_EXAM_NAME = " ";
    private static final String INVALID_EXAM_DATE_FORMAT = "23-9-2019";
    private static final String INVALID_EXAM_DATE_ALPHABETS = "abcdef";
    private static final String INVALID_SCORE_LARGER = "100/50";
    private static final String INVALID_SCORE_NEGATIVE = "-50/-100";
    private static final String INVALID_ATTENDANCE_DATE_FORMAT = "23-9-2019";
    private static final String INVALID_ATTENDANCE_DATE_ALPHABETS = "abcdef";
    private static final String INVALID_ATTENDANCE_STATUS = "you wot m8";
    private static final String INVALID_FEEDBACK = " ";
    private static final String INVALID_TITLE = "What is the meaning of life?";
    private static final String INVALID_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur"
            + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ali"
            + "qua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut "
            + "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in volupt"
            + "ate velit esse cillum dolore eu fugiat nulla pariatur.";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_SCHOOL = "Raffles Institution";
    private static final String VALID_YEAR = "JC 2";
    private static final String VALID_CLASS_VENUE = "Blk 411 #04-11, Lorong Chuan, Singapore 234332";
    private static final String VALID_CLASS_TIME = "3 1240-1530";
    private static final String VALID_FEE = "2350.30";
    private static final String VALID_PAYMENT_DATE = "23/9/2019";
    private static final String VALID_ADDITIONAL_DETAIL_WEEB = "Is a weeaboo";
    private static final String VALID_ADDITIONAL_DETAIL_CONVICT = "Just released from prison";
    private static final String VALID_QUESTION = "Why can't humans fly?";
    private static final String VALID_SOLUTION = "Read your textbook.";
    private static final String VALID_EXAM_NAME = "Mid Year 2020";
    private static final String VALID_EXAM_DATE = "23/9/2019";
    private static final String VALID_SCORE = "50/100";
    private static final String VALID_ATTENDANCE_DATE = "23/9/2019";
    private static final String VALID_ATTENDANCE_STATUS = "present";
    private static final String VALID_FEEDBACK = "attentive";
    private static final String VALID_TITLE = "meaning of life";
    private static final String VALID_DESCRIPTION = "Lorem ipsum dolor";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseSchool_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSchool(null));
    }

    @Test
    public void parseSchool_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSchool(INVALID_SCHOOL));
    }

    @Test
    public void parseSchool_validSchoolWithoutWhiteSpace_returnsSchool() throws Exception {
        School expectedSchool = new School(VALID_SCHOOL);
        assertEquals(expectedSchool, ParserUtil.parseSchool(VALID_SCHOOL));
    }

    @Test
    public void parseSchool_validSchoolWithWhiteSpace_returnsTrimmedSchool() throws Exception {
        String schoolWithSpaces = WHITESPACE + VALID_SCHOOL + WHITESPACE;
        School expectedSchool = new School(VALID_SCHOOL);
        assertEquals(expectedSchool, ParserUtil.parseSchool(schoolWithSpaces));
    }

    @Test
    public void parseYear_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseYear(null));
    }

    @Test
    public void parseYear_invalidYear_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseYear(INVALID_YEAR));
    }

    @Test
    public void parseYear_validYear_returnsYear() throws Exception {
        Year expectedYear = new Year(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(VALID_YEAR));
        assertEquals(expectedYear, ParserUtil.parseYear("J2")); // no whitespace
        assertEquals(expectedYear, ParserUtil.parseYear("J2               ")); // lots of trailing whitespace
        assertEquals(expectedYear, ParserUtil.parseYear("J         2               ")); // lots of whitespace
        assertEquals(expectedYear, ParserUtil.parseYear("Jc 2")); // only one letter capitalised
        assertEquals(expectedYear, ParserUtil.parseYear("jc 2")); // no letter capitalised
        assertEquals(expectedYear, ParserUtil.parseYear("jc 2")); // no letter capitalised
        assertEquals(expectedYear, ParserUtil.parseYear("j2")); // short form
    }

    @Test
    public void parseYear_validYearWithWhiteSpace_returnsTrimmedYear() throws Exception {
        String yearWithWhiteSpace = WHITESPACE + VALID_YEAR + WHITESPACE;
        Year expectedYear = new Year(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(yearWithWhiteSpace));
    }

    @Test
    public void parseVenue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassVenue(null));
    }

    @Test
    public void parseVenue_invalidVenue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSchool(INVALID_CLASS_VENUE));
    }

    @Test
    public void parseVenue_validVenueWithoutWhiteSpace_returnsVenue() throws Exception {
        ClassVenue expectedVenue = new ClassVenue(VALID_CLASS_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseClassVenue(VALID_CLASS_VENUE));
    }

    @Test
    public void parseVenue_validVenueWithWhiteSpace_returnsTrimmedVenue() throws Exception {
        String venueWithWhiteSpace = WHITESPACE + VALID_CLASS_VENUE + WHITESPACE;
        ClassVenue expectedVenue = new ClassVenue(VALID_CLASS_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseClassVenue(venueWithWhiteSpace));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassTime(null));
    }

    @Test
    public void parseTime_invalidTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClassTime(INVALID_CLASS_TIME));
    }

    @Test
    public void parseTime_validTimeWithoutWhiteSpace_returnsTime() throws Exception {
        ClassTime expectedTime = new ClassTime(VALID_CLASS_TIME);
        assertEquals(expectedTime, ParserUtil.parseClassTime(VALID_CLASS_TIME));
    }

    @Test
    public void parseTime_validTimeWithWhiteSpace_returnsTrimmedTime() throws Exception {
        String timeWithWhiteSpace = WHITESPACE + VALID_CLASS_TIME + WHITESPACE;
        ClassTime expectedTime = new ClassTime(VALID_CLASS_TIME);
        assertEquals(expectedTime, ParserUtil.parseClassTime(timeWithWhiteSpace));
    }

    @Test
    public void parseFee_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFee(null));
    }

    @Test
    public void parseFee_invalidFee_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFee(INVALID_FEE));
    }

    @Test
    public void parseFee_validFeeWithoutWhiteSpace_returnsFee() throws Exception {
        Fee expectedFee = new Fee(VALID_FEE);
        assertEquals(expectedFee, ParserUtil.parseFee(VALID_FEE));
    }

    @Test
    public void parseFee_validFeeWithWhiteSpaces_returnsTrimmedFee() throws Exception {
        String feeWithWhiteSpace = WHITESPACE + VALID_FEE + WHITESPACE;
        Fee expectedFee = new Fee(VALID_FEE);
        assertEquals(expectedFee, ParserUtil.parseFee(feeWithWhiteSpace));
    }

    @Test
    public void parsePaymentDate_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePaymentDate(null));
    }

    @Test
    public void parsePaymentDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePaymentDate(INVALID_PAYMENT_DATE));
    }

    @Test
    public void parsePaymentDate_validDateWithoutWhiteSpace_returnsPaymentDate() throws Exception {
        PaymentDate expectedPaymentDate = new PaymentDate(VALID_PAYMENT_DATE);
        assertEquals(expectedPaymentDate, ParserUtil.parsePaymentDate(VALID_PAYMENT_DATE));
    }

    @Test
    public void parsePaymentDate_validDateWithWhiteSpace_returnsTrimmedPaymentDate() throws Exception {
        String paymentDateWithWhiteSpace = WHITESPACE + VALID_PAYMENT_DATE + WHITESPACE;
        PaymentDate expectedPaymentDate = new PaymentDate(VALID_PAYMENT_DATE);
        assertEquals(expectedPaymentDate, ParserUtil.parsePaymentDate(paymentDateWithWhiteSpace));
    }

    @Test
    public void parseDetail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDetail(null));
    }

    @Test
    public void parseDetail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDetail(INVALID_ADDITIONAL_DETAIL));
    }

    @Test
    public void parseDetail_validValue_returnsDetail() throws Exception {
        Detail expectedDetail = new Detail(VALID_ADDITIONAL_DETAIL_WEEB);
        assertEquals(expectedDetail, ParserUtil.parseDetail(VALID_ADDITIONAL_DETAIL_WEEB));
    }

    @Test
    public void parseDetail_validValueWithWhiteSpace_returnsTrimmedDetail() throws Exception {
        String detailWithWhiteSpace = WHITESPACE + VALID_ADDITIONAL_DETAIL_WEEB + WHITESPACE;
        Detail expectedDetail = new Detail(VALID_ADDITIONAL_DETAIL_WEEB);
        assertEquals(expectedDetail, ParserUtil.parseDetail(detailWithWhiteSpace));
    }

    @Test
    public void parseDetails_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDetails(null));
    }

    @Test
    public void parseDetails_invalidDetail_throwsParseException() {
        List<String> invalidSet = List.of(INVALID_ADDITIONAL_DETAIL);
        assertThrows(ParseException.class, () -> ParserUtil.parseDetails(invalidSet));
    }

    @Test
    public void parseDetails_validDetails_returnsDetails() throws Exception {
        List<String> validList = List.of(VALID_ADDITIONAL_DETAIL_CONVICT, VALID_ADDITIONAL_DETAIL_WEEB);
        List<Detail> expectedSet = validList.stream()
                .map(Detail::new)
                .collect(Collectors.toList());
        assertEquals(expectedSet, ParserUtil.parseDetails(validList));
    }

    @Test
    public void parseDetails_validDetailsSpace_returnsTrimmedDetails() throws Exception {
        List<String> baseList = List.of(VALID_ADDITIONAL_DETAIL_CONVICT, VALID_ADDITIONAL_DETAIL_WEEB);
        List<String> validList = baseList.stream()
                .map(string -> WHITESPACE + string + WHITESPACE)
                .collect(Collectors.toList());
        List<Detail> expectedSet = baseList.stream()
                .map(Detail::new)
                .collect(Collectors.toList());
        assertEquals(expectedSet, ParserUtil.parseDetails(validList));
    }

    @Test
    public void parseQuestion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuestion((String) null));
    }

    @Test
    public void parseQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuestion(INVALID_QUESTION));
    }

    @Test
    public void parseQuestion_validValueWithoutWhiteSpace_returnsQuestion() throws Exception {
        Question expectedQuestion = new UnsolvedQuestion(VALID_QUESTION);
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(VALID_QUESTION));
    }

    @Test
    public void parseQuestion_validValueWithWhiteSpace_returnsTrimmedQuestion() throws Exception {
        String questionWithWhiteSpace = WHITESPACE + VALID_QUESTION + WHITESPACE;
        Question expectedQuestion = new UnsolvedQuestion(VALID_QUESTION);
        assertEquals(expectedQuestion, ParserUtil.parseQuestion(questionWithWhiteSpace));
    }

    @Test
    public void parseQuestion_valueValue_returnsUnresolvedQuestion() throws Exception {
        Question unexpectedQuestion = new SolvedQuestion(VALID_QUESTION, VALID_SOLUTION);
        assertNotEquals(unexpectedQuestion, ParserUtil.parseQuestion(VALID_QUESTION));
    }

    @Test
    public void parseSolution_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSolution(null));
    }

    @Test
    public void parseSolution_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSolution(INVALID_SOLUTION));
    }

    @Test
    public void parseSolution_validValueWithoutWhiteSpace_returnsSolution() throws Exception {
        assertEquals(VALID_SOLUTION, ParserUtil.parseSolution(VALID_SOLUTION));
    }

    @Test
    public void parseSolution_validValueWithWhiteSpace_returnsTrimmedSolution() throws Exception {
        String solutionWithSpace = WHITESPACE + VALID_SOLUTION + WHITESPACE;
        assertEquals(VALID_SOLUTION, ParserUtil.parseSolution(solutionWithSpace));
    }

    @Test
    public void parseExamName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExamName(null));
    }

    @Test
    public void parseExamName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamName(INVALID_EXAM_NAME));
    }

    @Test
    public void parseExamName_validExamNameWithoutWhiteSpace_returnsExamNameString() throws Exception {
        assertEquals(VALID_EXAM_NAME, ParserUtil.parseExamName(VALID_EXAM_NAME));
    }

    @Test
    public void parseExamName_validExamNameWithWhiteSpace_returnsTrimmedExamNameString() throws Exception {
        String examNameWithSpaces = WHITESPACE + VALID_EXAM_NAME + WHITESPACE;
        assertEquals(VALID_EXAM_NAME, ParserUtil.parseExamName(examNameWithSpaces));
    }

    @Test
    public void parseExamDate_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExamDate(null));
    }

    @Test
    public void parseExamDate_invalidDateWrongFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamDate(INVALID_EXAM_DATE_FORMAT));
    }

    @Test
    public void parseExamDate_invalidDateAlphabets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExamDate(INVALID_EXAM_DATE_ALPHABETS));
    }

    @Test
    public void parseExamDate_validDateWithoutWhiteSpace_returnsExamDateString() throws Exception {
        assertEquals(VALID_EXAM_DATE, ParserUtil.parseExamDate(VALID_EXAM_DATE));
    }

    @Test
    public void parseExamDate_validDateWithWhiteSpace_returnsTrimmedExamDateString() throws Exception {
        String examDateWithWhiteSpace = WHITESPACE + VALID_EXAM_DATE + WHITESPACE;
        assertEquals(VALID_EXAM_DATE, ParserUtil.parseExamDate(examDateWithWhiteSpace));
    }

    @Test
    public void parseScore_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScore(null));
    }

    @Test
    public void parseScore_invalidScoreFirstLargerThanSecond_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScore(INVALID_SCORE_LARGER));
    }

    @Test
    public void parseScore_invalidScoreNegative_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScore(INVALID_SCORE_NEGATIVE));
    }

    @Test
    public void parseScore_validScoreWithoutWhiteSpace_returnsScore() throws Exception {
        Score expectedScore = new Score(VALID_SCORE);
        assertEquals(expectedScore, ParserUtil.parseScore(VALID_SCORE));
    }

    @Test
    public void parseScore_validScoreWithoutWhiteSpace_returnsTrimmedScore() throws Exception {
        String scoreWithWhiteSpace = WHITESPACE + VALID_SCORE + WHITESPACE;
        Score expectedScore = new Score(VALID_SCORE);
        assertEquals(expectedScore, ParserUtil.parseScore(scoreWithWhiteSpace));
    }

    @Test
    public void parseAttendanceDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAttendanceDate(null));
    }

    @Test
    public void parseAttendanceDate_invalidDateWrongFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendanceDate(INVALID_ATTENDANCE_DATE_FORMAT));
    }

    @Test
    public void parseAttendanceDate_invalidDateAlphabets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendanceDate(INVALID_ATTENDANCE_DATE_ALPHABETS));
    }

    @Test
    public void parseAttendanceDate_validDateWithoutWhiteSpace_returnsExamDateString() throws Exception {
        assertEquals(VALID_EXAM_DATE, ParserUtil.parseExamDate(VALID_ATTENDANCE_DATE));
    }

    @Test
    public void parseAttendanceDate_validDateWithWhiteSpace_returnsTrimmedExamDateString() throws Exception {
        String attendanceDateWithWhiteSpace = WHITESPACE + VALID_ATTENDANCE_DATE + WHITESPACE;
        assertEquals(VALID_EXAM_DATE, ParserUtil.parseAttendanceDate(attendanceDateWithWhiteSpace));
    }

    @Test
    public void parseAttendanceStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAttendanceStatus(null));
    }

    @Test
    public void parseAttendanceStatus_invalidStatus_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAttendanceStatus(INVALID_ATTENDANCE_STATUS));
    }

    @Test
    public void parseAttendanceStatus_validStatusWithoutWhiteSpace_returnsStatusString() throws Exception {
        assertEquals(VALID_ATTENDANCE_STATUS, ParserUtil.parseAttendanceStatus(VALID_ATTENDANCE_STATUS));
    }

    @Test
    public void parseAttendanceStatus_validStatusWithWhiteSpace_returnsStatusString() throws Exception {
        String statusWithWhiteSpace = WHITESPACE + VALID_ATTENDANCE_STATUS + WHITESPACE;
        assertEquals(VALID_ATTENDANCE_STATUS, ParserUtil.parseAttendanceStatus(statusWithWhiteSpace));
    }

    @Test
    public void parseFeedback_invalidStatus_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFeedback(INVALID_FEEDBACK));
    }

    @Test
    public void parseFeedback_validStatusWithoutWhiteSpace_returnsFeedbackString() throws Exception {
        assertEquals(new Feedback(VALID_FEEDBACK), ParserUtil.parseFeedback(VALID_FEEDBACK));
    }

    @Test
    public void parseFeedback_validStatusWithWhiteSpace_returnsFeedbackString() throws Exception {
        String statusWithWhiteSpace = WHITESPACE + VALID_FEEDBACK + WHITESPACE;
        assertEquals(new Feedback(VALID_FEEDBACK), ParserUtil.parseFeedback(statusWithWhiteSpace));
    }

    @Test
    public void parseTitle_invalidTitle_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validTitleWithoutWhiteSpace_returnsTitleString() throws Exception {
        assertEquals(new Title(VALID_TITLE), ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validTitleWithWhiteSpace_returnsTitleString() throws Exception {
        String titleWithWhiteSpace = WHITESPACE + VALID_TITLE + WHITESPACE;
        assertEquals(new Title(VALID_TITLE), ParserUtil.parseTitle(titleWithWhiteSpace));
    }

    @Test
    public void parseDescription_invalidDescription_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validDescriptionWithoutWhiteSpace_returnsDescriptionString() throws Exception {
        assertEquals(new Description(VALID_DESCRIPTION), ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validDescriptionWithWhiteSpace_returnsDescriptionString() throws Exception {
        String descriptionWithWhiteSpace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        assertEquals(new Description(VALID_DESCRIPTION), ParserUtil.parseDescription(descriptionWithWhiteSpace));
    }

}
