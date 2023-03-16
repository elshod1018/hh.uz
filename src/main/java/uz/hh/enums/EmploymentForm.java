package uz.hh.enums;

public enum EmploymentForm {
    REMOTE("Remote"),
    IN_OFFICE("In Office"),
    HYBRID("Hybrid");

    private final String displayValue;

    EmploymentForm(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

