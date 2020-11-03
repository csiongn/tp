package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.admin.Detail;

/**
 * Jackson-friendly version of {@link Detail}.
 */
class JsonAdaptedDetail {

    private final String detail;

    /**
     * Constructs a {@code JsonAdaptedAdditionalDetail} with the given {@code detail}.
     */
    @JsonCreator
    public JsonAdaptedDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Converts a given {@code AdditionalDetail} into this class for Jackson use.
     */
    public JsonAdaptedDetail(Detail source) {
        detail = source.detail;
    }

    @JsonValue
    public String getDetail() {
        return detail;
    }

    /**
     * Converts this Jackson-friendly adapted additionalDetail object into the model's {@code AdditionalDetail} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted detail.
     */
    public Detail toModelType() throws IllegalValueException {
        if (!Detail.isValidAdditionalDetail(detail)) {
            throw new IllegalValueException(Detail.MESSAGE_CONSTRAINTS);
        }
        return new Detail(detail);
    }

}
