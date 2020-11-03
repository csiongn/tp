package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.student.admin.Fee.FREE_OF_CHARGE;
import static seedu.address.testutil.StudentBuilder.DEFAULT_FEE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotebook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Reeve;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.OverdueFeePredicate;
import seedu.address.testutil.StudentBuilder;

public class OverdueCommandTest {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d/M/yy");

    @Test
    public void execute_positiveFee() {
        // one student with overdue fees
        int studentsWhoHaveNotPaid = 1;
        Model model = getDateAdjustedModel(studentsWhoHaveNotPaid);
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, studentsWhoHaveNotPaid);
        Model expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());

        expectedModel.updateFilteredStudentList(new OverdueFeePredicate());

        assertCommandSuccess(new OverdueCommand(), model, expectedMessage, expectedModel);

        // multiple students with overdue fees
        studentsWhoHaveNotPaid = 3;
        model = getDateAdjustedModel(studentsWhoHaveNotPaid);
        expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, studentsWhoHaveNotPaid);
        expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());

        expectedModel.updateFilteredStudentList(new OverdueFeePredicate());

        assertCommandSuccess(new OverdueCommand(), model, expectedMessage, expectedModel);

        // no students with overdue fees
        studentsWhoHaveNotPaid = 0;
        model = getDateAdjustedModel(studentsWhoHaveNotPaid);
        expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, studentsWhoHaveNotPaid);
        expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), getTypicalNotebook());

        expectedModel.updateFilteredStudentList(new OverdueFeePredicate());

        assertCommandSuccess(new OverdueCommand(), model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_freeFee() {
        int freeloaders = 0;
        Model model = getFeeAdjustedModel(freeloaders);
        int studentsWhoHaveNotPaid = model.getSortedStudentList().size() - freeloaders;
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, studentsWhoHaveNotPaid);
        Model expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.updateFilteredStudentList(new OverdueFeePredicate());
        assertCommandSuccess(new OverdueCommand(), model, expectedMessage, expectedModel);

        freeloaders = 3;
        model = getFeeAdjustedModel(freeloaders);
        studentsWhoHaveNotPaid = model.getSortedStudentList().size() - freeloaders;
        expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, studentsWhoHaveNotPaid);
        expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), model.getNotebook());
        expectedModel.updateFilteredStudentList(new OverdueFeePredicate());
        assertCommandSuccess(new OverdueCommand(), model, expectedMessage, expectedModel);

    }

    /**
     * Returns a Model with {@code limit} number of students with overdue fees.
     */
    private static Model getDateAdjustedModel(int oneBasedLimit) {
        Reeve reeve = getTypicalAddressBook();
        int size = reeve.getStudentList().size();
        for (int i = 0; i < size; i++) {
            Student toReplace = reeve.getStudentList().get(i);
            LocalDate date;
            if (i < oneBasedLimit) {
                date = LocalDate.now().minusMonths(1).minusDays(1);
            } else {
                date = LocalDate.now().minusMonths(1).plusDays(1);
            }
            Student replacement = new StudentBuilder(toReplace).withPaymentDate(date.format(FORMAT)).build();
            reeve.setStudent(toReplace, replacement);
        }
        return new ModelManager(reeve, new UserPrefs(), getTypicalNotebook());
    }

    /**
     * Returns a Model with {@code limit} number of students with no fees.
     */
    private static Model getFeeAdjustedModel(int oneBasedLimit) {
        Reeve reeve = getTypicalAddressBook();
        int size = reeve.getStudentList().size();
        String date = LocalDate.now().minusMonths(1).minusDays(1).format(FORMAT);

        for (int i = 0; i < size; i++) {
            Student toReplace = reeve.getStudentList().get(i);
            String fee;
            if (i < oneBasedLimit) {
                fee = FREE_OF_CHARGE;
            } else {
                fee = DEFAULT_FEE;
            }
            Student replacement = new StudentBuilder(toReplace).withFee(fee).withPaymentDate(date).build();
            reeve.setStudent(toReplace, replacement);
        }
        return new ModelManager(reeve, new UserPrefs(), getTypicalNotebook());

    }

}
