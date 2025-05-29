package org.pluralsight.customer;
import org.pluralsight.Interface.I_Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich<T extends I_Topping> {
    private  String size;
    private String breadType;
    private String toasted; // changed from boolean
    private String meat, cheese ;
    private List<T> toppings = new ArrayList<>();
    private List<T> sauce = new ArrayList<>();

    public Sandwich(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
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
    public void addSauce(T s){
        sauce.add(s);
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
    public double getMeatPremium(){
        return switch (size){
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 0;
        };
    }
    public double getCheesePremium(){
        return switch (size){
            case "4" -> .75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 0;
        };

    }
    public double getExtraMeat() {
        return switch (size) {
            case "4" -> .50;
            case "8" -> 1.00;
            case "12" -> 1.50;
            default -> 0;
        };
    }
    public double getExtraCheese() {
        return switch (size) {
            case "4" -> .30;
            case "8" -> .60;
            case "12" -> .90;
            default -> 0;
        };
    }
    public double totalPrice(){
        return getBasePrice() + getMeatPremium() +
                getCheesePremium();
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

