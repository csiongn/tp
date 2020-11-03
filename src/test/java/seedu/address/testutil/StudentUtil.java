package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Detail;


/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ")
                .append(PREFIX_PHONE + student.getPhone().value + " ")
                .append(PREFIX_SCHOOL + student.getSchool().school + " ")
                .append(PREFIX_YEAR + String.valueOf(student.getYear()) + " ")
                .append(getAdminDetails(student));
        return sb.toString();
    }

    private static String getAdminDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        String venue = PREFIX_VENUE + student.getClassVenue().venue + " ";
        String time = PREFIX_TIME + String.valueOf(student.getClassTime().dayOfWeek.getValue())
                + " " + student.getClassTime().startTime.toString().replace(":", "")
                + "-" + student.getClassTime().endTime.toString().replace(":", "") + " ";
        String fee = PREFIX_FEE + String.valueOf(student.getFee().amount) + " ";
        String date = PREFIX_PAYMENT + student.getPaymentDate()
                .lastPaid
                .format(DateTimeFormatter.ofPattern("d/M/yy")) + " ";
        sb.append(venue).append(time).append(fee).append(date);

        for (Detail detail : student.getDetails()) {
            sb.append(PREFIX_DETAILS + detail.detail + " ");
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getSchool().ifPresent(school -> sb.append(PREFIX_SCHOOL).append(school.school).append(" "));
        descriptor.getYear().ifPresent(year -> sb.append(PREFIX_YEAR).append(year).append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code FindStudentDescriptor}'s details.
     */
    public static String getFindStudentDescriptorDetails(FindCommand.FindStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getNamePredicate().ifPresent(predicate -> {
            String stringName = predicate.keywords.stream() // Convert the keywords list in predicate into a string
                    .reduce("", (x, y) -> x + " " + y);
            sb.append(PREFIX_NAME).append(stringName).append(" ");
        });
        descriptor.getSchoolPredicate().ifPresent(predicate -> {
            String stringSchool = predicate.keywords.stream() // Convert the keywords list in predicate into a string
                    .reduce("", (x, y) -> x + " " + y);
            sb.append(PREFIX_SCHOOL).append(stringSchool).append(" ");
        });
        descriptor.getYearPredicate().ifPresent(predicate ->
                sb.append(PREFIX_YEAR).append(predicate.year).append(" "));

        return sb.toString();
    }
}
