package uz.hh.enums;

public enum Currency {
    DOLLAR("$ dollar"), EURO("€ euro"), SUM("sum");

    private final String displayValue;

    Currency(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
