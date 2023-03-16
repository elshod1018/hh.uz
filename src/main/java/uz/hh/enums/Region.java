package uz.hh.enums;

public enum Region {
    TASHKENT("Tashkent"),
    ANDIJAN("Andijan"),
    BUKHARA("Bukhara"),

    FERGANA("Fergana"),
    JIZZAKH("Jizzakh"),
    NAMANGAN("Namangan"),
    NAVOIY("Navoiy"),
    KASHKADARYA("Kashkadarya"),
    SAMARKAND("Samarkand"),
    SYRDARYA("Syrdarya"),
    SURKHANDARYA("Surkhandarya"),
    KHOREZM("Khorezm"),
    KARAKALPAKSTAN("Karakalpakstan");

    private final String displayValue;

    Region(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
