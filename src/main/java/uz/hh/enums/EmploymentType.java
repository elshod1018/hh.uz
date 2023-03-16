package uz.hh.enums;

public enum EmploymentType {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    FREELANCE("Freelance"),
    INTERNSHIP("Interenship");

    private final String displayValue;

    EmploymentType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
