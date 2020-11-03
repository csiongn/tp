package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setSchool(student.getSchool());
        descriptor.setYear(student.getYear());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code School} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withSchool(String school) {
        descriptor.setSchool(new School(school));
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withYear(String year) {
        descriptor.setYear(new Year(year));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
