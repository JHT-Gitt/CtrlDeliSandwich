package org.example.screens;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.example.Interface.Extras;
import org.example.Interface.I_Topping;
import org.example.Model.CheeseTopping;
import org.example.Model.MeatTopping;
import org.example.Model.Topping;
import org.example.customer.Login;
import org.example.customer.Sandwich;
import org.example.fileHandler.Receipt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Order implements Extras {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    Scanner scanner = new Scanner(System.in);

    public String size = "";
    public String breadType= "";
    public String isToasted;
    public String meatPremium = "";
    public String cheesePremium = "";
    public String exMeat, exCheese;

    List<String> allReceipts = new ArrayList<>();
    List<Topping> meatTop = new ArrayList<>();
    List<Topping> cheeseTop = new ArrayList<>();
    Sandwich<I_Topping> sandwich = new Sandwich<>(size);
    private Login user;

    public Order(Login user) {
        this.user = user;
    }

    public void addSandwich(){
        int enter;

        lines();
        System.out.println(YELLOW + "Select Sandwich Bread type:" + RESET);
        System.out.println(" 1 - White ");
        System.out.println(" 2 - Wheat");
        System.out.println(" 3 - Rye");
        System.out.println(" 4 - Wrap");
        lines();
        while(true) {
            try {
                System.out.print("Enter: ");
                enter = scanner.nextInt();
                scanner.nextLine();
                if (enter >= 1 && enter <= 4) {
                    breadType = switch (enter) {
                        case 1 -> "White";
                        case 2 -> "Wheat";
                        case 3 -> "Rye";
                        case 4 -> "Wrap";
                        default -> "";
                    };
                    break;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        sandwich.setBreadType(breadType);
        size();

    }
    public void size(){

        double s = 5.50;
        double m = 7.00;
        double l = 8.50;
        String indent = "        ";
        lines();
        System.out.printf(indent + YELLOW +"%-12s|" + indent +"%s\n" + RESET, "Size", "Price" );
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "1 - 4\"", s);
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "2 - 8\"", m);
        System.out.printf(indent + "%-12s|" + indent +"$%.2f\n", "3 - 12\"", l);
        lines();
        while (true) {
            try {
                System.out.print("Enter:");
                int enter = scanner.nextInt();
                scanner.nextLine();
                if (enter >= 1 && enter <= 3) {
                    size = switch (enter) {
                        case 1 -> "4";
                        case 2 -> "8";
                        case 3 -> "12";
                        default -> "";
                    };
                    break;
                } else {
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.print(GREEN + "Do you want it toasted [Y/N] ?: " + RESET);
            String toastedInput = scanner.nextLine().trim().toLowerCase();

            if (toastedInput.equals("yes") || toastedInput.equals("y")) {
                isToasted = "Yes";
                break;
            } else if (toastedInput.equals("no") || toastedInput.equals("n")) {
                isToasted = "No";
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        sandwich.setToasted(isToasted);
        meat();

    }
    public void meat(){
        meatTop.clear();
        meatTop.add(new Topping("Steak"));
        meatTop.add(new Topping("Ham"));
        meatTop.add(new Topping("Salami"));
        meatTop.add(new Topping("Roast Beef"));
        meatTop.add(new Topping("Chicken"));
        meatTop.add(new Topping("Bacon"));

        double s = 1.00;
        double m = 2.00;
        double l = 3.00;
        String indent = "   ";
        boolean isFound= false;


        System.out.println("\n========== 🥩 " + YELLOW + "MEAT TOPPINGS"  + RESET + " 🥩 ===========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        for (Topping t : meatTop) {
            System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", t.getName(), s, m, l);
        }
        lines();

        while(!isFound) {
            System.out.print("Enter: ");
            String meat = scanner.nextLine();

            for (Topping t : meatTop) {
                if (t.getName().equalsIgnoreCase(meat)) {
                        meatPremium=meat;
                        isFound = true;
                        break;
                }
            }
            if(!isFound){
                System.out.println("Invalid input. Please try again");
        }
        }


        sandwich.addTopping(new MeatTopping(meatPremium));



        extraMeat();
        cheese();

    }
    public void cheese(){
        cheeseTop.clear();
        cheeseTop.add(new Topping("American"));
        cheeseTop.add(new Topping("Provolone"));
        cheeseTop.add(new Topping("Cheddar"));
        cheeseTop.add(new Topping("Swiss"));

        double s = .75;
        double m = 1.50;
        double l = 2.25;
        boolean isFound = false;
        String indent = "   ";
        System.out.println("\n========= 🧀 " + YELLOW + "CHEESE TOPPINGS"  + RESET + " 🧀 ==========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        for (Topping c : cheeseTop) {
            System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", c.getName(), s, m, l);
        }
        lines();

        while(!isFound) {
            System.out.print("Enter: ");
            String cheese = scanner.nextLine();

            for (Topping t : cheeseTop) {
                if (t.getName().equalsIgnoreCase(cheese)) {
                    cheesePremium=cheese;
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                System.out.println("Invalid input. Please try again");
            }
        }
        sandwich.addTopping(new CheeseTopping(cheesePremium));

        extraCheese();
        printReceipt();
    }

public void lines(){
    System.out.println("===========================================");

}
    @Override
    public void extraMeat() {
        double s = .50;
        double m = 1.00;
        double l = 1.50;
        System.out.printf("Extra Meat  |   %.2f | %.2f | %.2f", s,m,l);

        while (true) {
            System.out.print(GREEN + "\nAdd Extra Meat [Y/N] ? : "  + RESET );
            exMeat = scanner.nextLine().trim().toLowerCase();

            if (exMeat.equals("yes") || exMeat.equals("no") ||
                    exMeat.equals("y") || exMeat.equals("n")) {
                System.out.println("Extra meat added !");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if (exMeat.equals("yes") || exMeat.equals("y")) {
            sandwich.addTopping(new MeatTopping(meatPremium, true));
        }
        lines();
    }

    @Override
    public void extraCheese() {
        double s = .30;
        double m = .60;
        double l = .90;
        System.out.printf("Extra Cheese  |   %.2f | %.2f | %.2f" , s,m,l);

        while (true) {
            System.out.print(GREEN + "\nAdd Extra Cheese [Y/N] ? : "  + RESET );
            exCheese = scanner.nextLine().trim().toLowerCase();

            if (exCheese.equals("yes") || exCheese.equals("no") ||
                    exCheese.equals("y") || exCheese.equals("n")) {
                System.out.println("Extra cheese added !");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if (exCheese.equals("yes") || exCheese.equals("y")) {
            sandwich.addTopping(new CheeseTopping(cheesePremium, true));
        }
        lines();

    }
    public void printReceipt(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String timestamp = now.format(formatter);
        int orderNumber = new Random().nextInt(90000) + 10000;

        System.out.println("\n--- RECEIPT ---");
        String receipt = "\n--- RECEIPT ---\n" +
                "Name: " + user.getFirstname() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Order #: " + orderNumber + "\n" +
                "Time: " + timestamp + "\n" +
                "Bread Type: " + breadType + "\n" +
                "Size: " + size + "\n" +
                "Toasted: " + isToasted + "\n" +
                "Meat Premium: " + meatPremium + "\n" +
                "Extra Meat: " + exMeat + "\n" +
                "Cheese Premium: " + cheesePremium + "\n" +
                "Extra Cheese: " + exCheese;

        allReceipts.add(receipt); // ⬅️ Store the receipt in the list
        Receipt.writeReceipt(receipt);
        printAllReceipts();
       // System.out.println(receipt);
//        String receipt = "Bread Type: " + breadType + "\n" +
//                "Size: " + size + "\n" +
//                "Toasted: " + isToasted + "\n" +
//                "Meat Premium: " + meatPremium + "\n" +
//                "Extra Meat: " + exMeat + "\n" +
//                "Cheese Premium: " + cheesePremium + "\n" +
//                "Extra Cheese: " + exCheese
//                ;
//                //sandwich.getReceipt();
//        System.out.println(receipt);
//
//        try (FileWriter writer = new FileWriter("src/main/resources/receipt.csv",true)) {
//            writer.write(receipt);
//            System.out.println("Receipt saved to receipt.csv");
//        } catch (IOException e) {
//            System.out.println("Error saving receipt: " + e.getMessage());
//        }
    }
    public void printAllReceipts() {
        System.out.println("\n========= ALL RECEIPTS =========");
        int count = 1;
        for (String receipt : allReceipts) {
            System.out.println("Receipt #" + count++);
            System.out.println(receipt);
            System.out.println("--------------------------------");
        }
    }

}
