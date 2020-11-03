package seedu.address.model.student.admin;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents all administrative details of a Student in Reeve.
 * Consists of the lesson venue, time, monthly fees, last paid date and additional notes.
 * Guarantees: details are present and non-null, all fields are validated and immutable.
 */
public class Admin {

    private final ClassVenue classVenue;
    private final ClassTime classTime;
    private final Fee fee;
    private final PaymentDate paymentDate;
    private final List<Detail> details = new ArrayList<>();

    /**
     * venue, time, fee, date and details are not null.
     */
    public Admin(ClassVenue venue, ClassTime time, Fee fee, PaymentDate date,
                 List<Detail> details) {
        requireAllNonNull(venue, time, fee, date, details);
        this.classVenue = venue;
        this.classTime = time;
        this.fee = fee;
        this.paymentDate = date;
        this.details.addAll(details);
    }

    public ClassVenue getClassVenue() {
        return classVenue;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public Fee getFee() {
        return fee;
    }

    public PaymentDate getPaymentDate() {
        return paymentDate;
    }

    public List<Detail> getDetails() {
        return new ArrayList<>(details);
    }

    /**
     * Get details of student formatted for GUI use.
     * @return formatted details.
     */
    public String getFormattedDetails() {
        String result = "";
        for (Detail detail: details) {
            result = result + "- " + detail.toString() + "\n";
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classVenue, classTime, fee, paymentDate, details);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Admin)) {
            return false;
        }

        Admin other = (Admin) obj;
        return other.getClassVenue().equals(getClassVenue())
                && other.getClassTime().equals(getClassTime())
                && other.getFee().equals(getFee())
                && other.getPaymentDate().equals(getPaymentDate())
                && other.getDetails().equals(getDetails());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nClass Venue: ")
                .append(classVenue)
                .append("\nLesson Times: ")
                .append(classTime)
                .append("\nFee: ")
                .append(fee)
                .append("\nLast Paid: ")
                .append(paymentDate);

        if (!details.isEmpty()) {
            builder.append("\nDetails:\n");
            String detailList = details.stream()
                    .map(detail -> String.format("- %s", detail))
                    .collect(Collectors.joining("\n"));
            builder.append(detailList);
        }
        return builder.toString();
    }
}
