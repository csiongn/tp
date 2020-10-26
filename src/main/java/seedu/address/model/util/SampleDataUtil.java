package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.Reeve;
import seedu.address.model.notes.Notebook;
import seedu.address.model.notes.ReadOnlyNotebook;
import seedu.address.model.notes.note.Description;
import seedu.address.model.notes.note.Note;
import seedu.address.model.notes.note.Title;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.SchoolType;
import seedu.address.model.student.Student;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.Admin;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

/**
 * Contains utility methods for populating {@code Reeve} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"),
                    new School("NUS High School"), new Year(SchoolType.SECONDARY, 4),
                    new Admin(new ClassVenue("Blk 30 Geylang Street 29, #06-40"),
                            new ClassTime("1 1400-1500"), new Fee("430"),
                            new PaymentDate("23/4/19"), getDetailList("clever")),
                    getQuestions("How do birds fly?")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"),
                    new School("Montford Secondary School"), new Year(SchoolType.SECONDARY, 4),
                    new Admin(new ClassVenue("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                            new ClassTime("1 1500-1600"), new Fee("50"),
                            new PaymentDate("30/6/20"), getDetailList()),
                    getSolvedQuestions("Read your textbook", "Explain heat flow.")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new School("Raffles Girls School"), new Year(SchoolType.SECONDARY, 3),
                    new Admin(new ClassVenue("Blk 11 Ang Mo Kio Street 74, #11-04"),
                            new ClassTime("2 1900-1930"), new Fee("680"),
                            new PaymentDate("1/12/19"), getDetailList()),
                    getQuestions()),
            new Student(new Name("David Li"), new Phone("91031282"),
                    new School("Anderson Primary School"), new Year(SchoolType.PRIMARY, 2),
                    new Admin(new ClassVenue("Blk 436 Serangoon Gardens Street 26, #16-43"),
                            new ClassTime("6 0800-0950"), new Fee("12"),
                            new PaymentDate("24/7/20"), getDetailList("friend")),
                    getQuestions("How do birds fly?", "Explain heat flow.")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new School("National Junior College"), new Year(SchoolType.JC, 1),
                    new Admin(new ClassVenue("Blk 47 Tampines Street 20, #17-35"),
                            new ClassTime("3 1300-1400"), new Fee("0"),
                            new PaymentDate("7/4/20"), getDetailList("clever", "friend")),
                    getQuestions()),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new School("Catholic High School"), new Year(SchoolType.JC, 1),
                    new Admin(new ClassVenue("Blk 45 Aljunied Street 85, #11-31"),
                            new ClassTime("4 2000-2130"), new Fee("38"),
                            new PaymentDate("19/12/19"), getDetailList("rude")),
                    getQuestions())
        };
    }

    public static Note[] getSampleNotes() {
        return new Note[] {
            new Note(new Title("Finish tp"), new Description("It's week 11!!")),
            new Note(new Title("Study for finals"), new Description("or die during reading week :(")),
            new Note(new Title("relax"), new Description("Eat, watch youtube, go USS"))
        };
    }

    public static ReadOnlyReeve getSampleAddressBook() {
        Reeve sampleAb = new Reeve();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    public static ReadOnlyNotebook getSampleNotebook() {
        Notebook sampleNotebook = new Notebook();
        for (Note sampleNote : getSampleNotes()) {
            sampleNotebook.addNote(sampleNote);
        }
        return sampleNotebook;
    }

    /**
     * Returns a {@code AdditionalDetail} list containing the list of given strings.
     */
    public static List<AdditionalDetail> getDetailList(String... strings) {
        return Arrays.stream(strings)
                .map(AdditionalDetail::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a {@code Question} list containing the list of given strings.
     */
    public static List<Question> getQuestions(String... strings) {
        return Arrays.stream(strings)
                .map(UnsolvedQuestion::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of solved {@code Questions} containing the given strings.
     */
    public static List<Question> getSolvedQuestions(String solution, String... strings) {
        return Arrays.stream(strings)
                .map(string -> new SolvedQuestion(string, solution))
                .collect(Collectors.toList());
    }

}
