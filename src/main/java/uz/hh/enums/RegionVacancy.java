package uz.hh.enums;

public enum RegionVacancy {
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

    RegionVacancy(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
