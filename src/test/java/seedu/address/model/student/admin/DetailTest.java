package seedu.address.model.student.admin;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Detail(null));
    }

    @Test
    public void constructor_invalidAdditionalDetail_throwsIllegalArgumentException() {
        String invalidAdditionalDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new Detail(invalidAdditionalDetail));
    }

    @Test
    public void isValidAdditionalDetail() {
        // null detail
        assertThrows(NullPointerException.class, () -> Detail.isValidAdditionalDetail(null));
    }

}
