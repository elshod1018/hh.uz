package uz.hh.enums;

public enum Region {
    TASHKENT("tashkent.city"),
    TASHKENTVILOYATI("tashkent.region"),
    QARAQALPAQSTAN("qq.res"),
    ANDIJAN("andijan.reg"),
    FERGANA("fergana.reg"),
    NAMANGAN("namangan.reg"),
    JIZZAX("jizzakh.reg"),
    QASHQADARYO("kashkadarya.reg"),
    SAMARKAND("samarkand.reg"),
    NAVOIY("navai.reg"),
    BUXARA("bukhara.reg"),
    XOREZM("khorezm.reg"),
    SURXANDARYA("surkhandarya.reg"),
    SIRDARYA("syrdarya.reg");


    private String regionName;

    Region(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }
}
