package org.example.customer;
import org.example.Interface.I_Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich<T extends I_Topping> {
    private final String size;
    private String breadType;
    private String toasted; // changed from boolean
    private String meat, cheese, regularToppings, sauce ;
    private final List<T> toppings = new ArrayList<>();

    public Sandwich(String size) {
        this.size = size;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public void setToasted(String toasted) {
        this.toasted = toasted;
    }

    public void addTopping(T topping) {
        toppings.add(topping);
    }

    public double getBasePrice() {
        return switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0;
        };
    }
//
//    public String getReceipt() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Bread Type: ").append(breadType).append(toppings).append("\n");
//        sb.append("Size: ").append(size).append(" inch\n");
//        sb.append("Toasted: ").append(toasted).append("\n");
//        sb.append("Base Price: $").append(String.format("%.2f", getBasePrice())).append("\n");
//
//        double total = getBasePrice();
//        for (T topping : toppings) {
//            double price = topping.getPrice(size);
//            sb.append(topping.getName()).append(": $").append(String.format("%.2f", price)).append("\n");
//            total += price;
//        }
//
//        sb.append("-----------------------\n");
//        sb.append("Total: $").append(String.format("%.2f", total)).append("\n");
//        return sb.toString();
//    }
}

