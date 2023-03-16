package uz.hh.enums;

public enum Market {
    INFORMATION_TECHNOLOGY("Information technology"),
    SOFTWARE_DEVELOPMENT("Software Development"),
    MANAGEMENT("Management"),
    DESIGN("Design"),
    WEB_DEVELOPMENT("Web development"),
    EDUCATION("Education"),
    SALES("Sales"),
    MARKETING("Marketing"),
    TRAVEL("Travel"),
    DEVOPS("DevOps"),
    ART("Art"),
    SCIENCE("Science");

    private final String displayValue;

    Market(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
