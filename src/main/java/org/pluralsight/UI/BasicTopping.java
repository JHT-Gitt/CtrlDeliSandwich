package org.pluralsight.UI;

public class BasicTopping implements ITopping{
    private String name;

    public BasicTopping(String name) {
        this.name = name;
    }

    @Override
    public double getPrice(String size) {
        return 0.0;
    }

    @Override
    public String getDescription() {
        return name;
    }
}
