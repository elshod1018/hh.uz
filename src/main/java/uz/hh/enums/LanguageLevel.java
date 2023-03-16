package uz.hh.enums;

public enum LanguageLevel {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    UPPER_INTERMEDIATE("Upper Intermediate"),
    NATIVE("Native"),
    PROFICIENT("Proficient");

    private final String displayValue;

    LanguageLevel(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
