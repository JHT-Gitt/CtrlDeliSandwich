package org.example.Model;

import org.example.Interface.I_Topping;

public class MeatTopping implements I_Topping {
    private String type;
    private boolean isExtra;

    public MeatTopping(String type) {
        this(type, false);
    }

    public MeatTopping(String type, boolean isExtra) {
        this.type = type;
        this.isExtra = isExtra;
    }

    @Override
    public double getPrice(String size) {

        double price = switch (type.toLowerCase()){
            case "steak", "ham", "salami", "roast beef", "chicken", "bacon" -> switch (size) {
                case "4" -> 1.00;
                case "8" -> 2.00;
                case "12" -> 3.00;
                default -> 0;
            };
            default -> 0;
        };
        return isExtra ? price * .50 : price;

    }

    @Override
    public String getName() {
        return isExtra ? type + " (extra)": type;
    }
}
