package uz.hh.enums;

public enum Region {
    TASHKENT("Toshkent shahri"),
    TASHKENTVILOYATI("Tashkent viloyati"),
    QARAQALPAQSTAN("Qaraqalpaqstan Respublikasi"),
    ANDIJAN("Andijon viloyati"),
    FERGANA("Farg'ona viloyati"),
    NAMANGAN("Namangan viloyati"),
    JIZZAX("Jizzax viloyati"),
    QASHQADARYO("Qashqadaryo viloyati"),
    SAMARKAND("Samarqand viloyati"),
    NAVOIY("Navoiy viloyati"),
    BUXARA("Buxoro viloyati"),
    XOREZM("Xorazm viloyati"),
    SURXANDARYA("Surxandaryo viloyati"),
    SIRDARYA("Sirdaryo viloyati");


    private String regionName;

    Region(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }
}
