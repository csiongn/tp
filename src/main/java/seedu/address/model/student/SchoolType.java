package seedu.address.model.student;

import java.util.Map;

public enum SchoolType {

    PRIMARY {
        @Override
        public String toString() {
            return "Primary";
        }
    },
    SECONDARY {
        @Override
        public String toString() {
            return "Secondary";
        }
    },
    JC {
        @Override
        public String toString() {
            return "JC";
        }
    };

    public static final Map<String, SchoolType> LOOKUP_TABLE = Map.ofEntries(
            Map.entry("pri", PRIMARY),
            Map.entry("primary", PRIMARY),
            Map.entry("p", PRIMARY),
            Map.entry("sec", SECONDARY),
            Map.entry("secondary", SECONDARY),
            Map.entry("s", SECONDARY),
            Map.entry("jc", JC),
            Map.entry("j", JC)
    );

    public static boolean isValidSchoolType(String schoolType) {
        return LOOKUP_TABLE.containsKey(schoolType.toLowerCase());
    }

}
