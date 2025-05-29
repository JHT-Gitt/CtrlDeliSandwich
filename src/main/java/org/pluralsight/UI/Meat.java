package org.pluralsight.UI;

public class Meat implements ITopping{
    private String type;
    private boolean extra;

    public Meat(String type) {
        this(type, false);
    }

    public Meat(String type, boolean extra) {
        this.type = type;
        this.extra = extra;
    }

    @Override
    public double getPrice(String size) {
        double base = switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 0;
        };
        return extra ? base / 2 : base;
    }

    @Override
    public String getDescription() {
        return type + (extra ? " (Extra)" : "");
    }
}
