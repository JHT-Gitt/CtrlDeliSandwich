package org.pluralsight.Model;

import org.pluralsight.Interface.I_Topping;

public class CheeseTopping implements I_Topping {
    private String type;
    private boolean isExtra;

    public CheeseTopping(String type) {
        this(type, false);
    }

    public CheeseTopping(String type, boolean isExtra) {
        this.type = type;
        this.isExtra = isExtra;
    }

    @Override
    public double getPrice(String size) {

        double price = switch (type.toLowerCase()){
          case "american", "provolone","cheddar", "swiss" -> switch (size){
            case "4" -> .75;
            case "8" -> 1.50;
            case "12" -> 2.25;
              default -> 0;
          };
            default -> 0;
        };
        return isExtra ? price * 0.60 : price;
    }

    @Override
    public String getName() {
        return isExtra ? type + " (extra)": type;
    }
}
