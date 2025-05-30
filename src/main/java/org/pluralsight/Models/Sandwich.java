package org.pluralsight.Models;

import org.pluralsight.Interface.ITopping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich<T extends ITopping> {

    private String size;
    private String breadType;
    private String toasted;
    private ArrayList<T> toppings;

    public Sandwich(String size) {
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public void setSize(String size) {
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
    public double getTotalPrice() {
        double total = getBasePrice();
        for (T topping : toppings) {
            total += topping.getPrice(size);
        }
        return total;
    }

public String getReceipt() {
    StringBuilder sb = new StringBuilder();

    sb.append("\n--- SANDWICH RECEIPT ---\n");
    sb.append("Bread Type: ").append(breadType).append("\n");
    sb.append("Size: ").append(size).append("\" - $")
            .append(String.format("%.2f", getBasePrice())).append("\n");
    sb.append("Toasted: ").append(toasted).append("\n");

    List<ITopping> premiumToppings = new ArrayList<>();
    List<ITopping> basicToppings = new ArrayList<>();

    for (T topping : toppings) {
        if (topping instanceof Meat || topping instanceof Cheese) {
            premiumToppings.add(topping);
        } else if (topping instanceof BasicTopping) {
            basicToppings.add(topping);
        }
    }

    if (!premiumToppings.isEmpty()) {
        sb.append("\nPremium Toppings:\n");
        for (ITopping topping : premiumToppings) {
            sb.append("  - ").append(topping.getDescription())
                    .append(": $").append(String.format("%.2f", topping.getPrice(size))).append("\n");
        }
    }

    if (!basicToppings.isEmpty()) {
        sb.append("\nRegular Toppings:\n");
        for (ITopping topping : basicToppings) {
            sb.append("  - ").append(topping.getDescription()).append("\n");
        }
    }

    sb.append("\nSandwich Total: $").append(String.format("%.2f", getTotalPrice())).append("\n");
    sb.append("--------------------------\n");

    return sb.toString();
}





}
