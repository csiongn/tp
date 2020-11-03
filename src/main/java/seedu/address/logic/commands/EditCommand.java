package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.exam.Exam;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;

/**
 * Edits the details of an existing student in Reeve.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_SCHOOL + "SCHOOL] "
            + "[" + PREFIX_YEAR + "YEAR]"
            + "[" + PREFIX_VENUE + "CLASS_VENUE]"
            + "[" + PREFIX_TIME + "CLASS_TIME]"
            + "[" + PREFIX_FEE + "FEE]"
            + "[" + PREFIX_PAYMENT + "PAYMENT_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_VENUE + "Anderson Junior College"
            + PREFIX_TIME + "2 1300-1400";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in Reeve.";


    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;
    private final EditAdminDescriptor editAdminDescriptor;

    /**
     * @param index of the student in the sorted student list to edit
     * @param editStudentDescriptor details to edit the student with
     * @param editAdminDescriptor admin details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor,
                       EditAdminDescriptor editAdminDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
        this.editAdminDescriptor = new EditAdminDescriptor(editAdminDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getSortedStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor, editAdminDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        ClassTime studentToEditClassTime = studentToEdit.getAdmin().getClassTime();
        ClassTime editedStudentClassTime = editedStudent.getAdmin().getClassTime();
        if (!(editedStudentClassTime.equals(studentToEditClassTime)) && model.isClashingClassTime(editedStudent)) {
            throw new CommandException(Messages.MESSAGE_CLASHING_LESSON);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studenttoEdit}
     * edited with {@code editStudentDescriptor} and {@code editAdminDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor,
                                               EditAdminDescriptor editAdminDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        School updatedSchool = editStudentDescriptor.getSchool().orElse(studentToEdit.getSchool());
        Year updatedYear = editStudentDescriptor.getYear().orElse(studentToEdit.getYear());

        // Questions should not be edited through this command
        List<Question> questions = studentToEdit.getQuestions();
        // Exams should not be edited through this command
        List<Exam> exams = studentToEdit.getExams();
        // Academic should not be edited through this command
        Academic academic = studentToEdit.getAcademic();

        if (editAdminDescriptor.isAnyFieldEdited()) {
            ClassTime updatedClassTime = editAdminDescriptor.getClassTime()
                    .orElse(studentToEdit.getClassTime());
            ClassVenue updatedClassVenue = editAdminDescriptor.getClassVenue()
                    .orElse(studentToEdit.getClassVenue());
            Fee updatedFee = editAdminDescriptor.getFee()
                    .orElse(studentToEdit.getFee());
            PaymentDate updatedPaymentDate = editAdminDescriptor.getPaymentDate()
                    .orElse(studentToEdit.getPaymentDate());

            // Additional Details cannot be edited through this channel
            return new Student(updatedName, updatedPhone, updatedSchool, updatedYear,
                    updatedClassVenue, updatedClassTime, updatedFee, updatedPaymentDate, studentToEdit.getDetails(),
                    questions, academic);
        } else {
            return new Student(updatedName, updatedPhone, updatedSchool, updatedYear, studentToEdit.getAdmin(),
                    questions, academic);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {

        private Name name;
        private Phone phone;
        private School school;
        private Year year;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setSchool(toCopy.school);
            setYear(toCopy.year);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, school, year);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setSchool(School school) {
            this.school = school;
        }

        public Optional<School> getSchool() {
            return Optional.ofNullable(school);
        }

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getSchool().equals(e.getSchool())
                    && getYear().equals(e.getYear());
        }
    }

    public static class EditAdminDescriptor {

        private ClassTime time;
        private ClassVenue venue;
        private Fee fee;
        private PaymentDate paymentDate;

        public EditAdminDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code details} is used internally.
         */
        public EditAdminDescriptor(EditAdminDescriptor toCopy) {
            setTime(toCopy.time);
            setVenue(toCopy.venue);
            setFee(toCopy.fee);
            setPaymentDate(toCopy.paymentDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(time, venue, fee, paymentDate);
        }

        public void setTime(ClassTime time) {
            this.time = time;
        }

        public Optional<ClassTime> getClassTime() {
            return Optional.ofNullable(time);
        }

        public void setVenue(ClassVenue venue) {
            this.venue = venue;
        }

        public Optional<ClassVenue> getClassVenue() {
            return Optional.ofNullable(venue);
        }

        public void setFee(Fee fee) {
            this.fee = fee;
        }

        public Optional<Fee> getFee() {
            return Optional.ofNullable(fee);
        }

        public void setPaymentDate(PaymentDate paymentDate) {
            this.paymentDate = paymentDate;
        }

        public Optional<PaymentDate> getPaymentDate() {
            return Optional.ofNullable(paymentDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAdminDescriptor)) {
                return false;
            }

            // state check
            EditAdminDescriptor e = (EditAdminDescriptor) other;

            return getClassVenue().equals(e.getClassVenue())
                    && getClassTime().equals(e.getClassTime())
                    && getFee().equals(e.getFee())
                    && getPaymentDate().equals(e.getPaymentDate());
        }
    }

}
