package uz.hh.enums;

public enum Language {
    UZBEK("Uzbek"),
    RUSSIAN("Russian"),
    ENGLISH("English");
    private final String displayValue;

    Language(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
