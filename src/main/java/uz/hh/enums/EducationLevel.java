package uz.hh.enums;

public enum EducationLevel {
    BACHELORS("Bachelor's"),
    MASTERS("Masters"),
    NONE("None"),
    HIGH_SCHOOL("High  school");
    private final String displayValue;

    EducationLevel(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
    }
