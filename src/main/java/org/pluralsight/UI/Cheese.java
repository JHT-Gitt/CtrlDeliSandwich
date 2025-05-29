package org.pluralsight.UI;

public class Cheese implements ITopping {
    private String type;
    private boolean extra;

    public Cheese(String type) {
        this(type, false);
    }

    public Cheese(String type, boolean extra) {
        this.type = type;
        this.extra = extra;
    }

    @Override
    public double getPrice(String size) {
        double base = switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 0;
        };
        return extra ? base - (base *.6) : base;
    }

    @Override
    public String getDescription() {
        return type + (extra ? " (Extra)" : "");
    }
}
