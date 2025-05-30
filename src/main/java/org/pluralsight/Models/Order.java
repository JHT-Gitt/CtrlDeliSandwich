package org.pluralsight.Models;

import org.pluralsight.Interface.ITopping;
import org.pluralsight.customer.Login;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {

    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";


    Scanner scanner = new Scanner(System.in);
    public String breadType= "";
    public String size = "";
    public String isToasted;
    public String meatPremium = "";
    public String cheesePremium = "";
    public String exMeat, exCheese;
    public double grandTotal = 0;
    public StringBuilder receipt;
    public String leftIndent = "        ";

    private Login user;
    // Item collections
    private final List<String> drinksOrdered = new ArrayList<>();
    private final List<String> chipsOrdered = new ArrayList<>();
    private double drinksTotal = 0;
    private double chipsTotal = 0;

    private final List<Sandwich<ITopping>> sandwichOrders = new ArrayList<>();
    List<Toppings> premiumMeat = new ArrayList<>();
    List<Toppings> premiumCheese = new ArrayList<>();
    List<String> allReceipts = new ArrayList<>();

    public Order(Login user){
        this.user = user; // Assign user (guest or member) to the order
    }

    // Begin sandwich order process
    public void addSandwich(){
        Sandwich<ITopping> sandwich = new Sandwich<>(size);
        int enter;
        // Bread type selection
        lines();
        System.out.println(YELLOW + "Select Sandwich Bread type:" + RESET);
        System.out.println(" 1 - White ");
        System.out.println(" 2 - Wheat");
        System.out.println(" 3 - Rye");
        System.out.println(" 4 - Wrap");
        lines();
        while(true) {
            try {
                System.out.print("Enter bread type: ");
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
        sandwich.setBreadType(breadType); // Set the breadtype to sandwich
        breadSize(sandwich);  // Proceed to select sandwich size
    }

    // Choose size and price of sandwich
    public void breadSize(Sandwich<ITopping> sandwich){
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
                System.out.print("Enter bread size: ");
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
        lines();
        sandwich.setSize(size); // Store and set the bread size

        meat(sandwich);
    }
    public void meat(Sandwich<ITopping> sandwich){
        premiumMeat.clear();
        premiumMeat.add(new Toppings("Steak"));
        premiumMeat.add(new Toppings("Ham"));
        premiumMeat.add(new Toppings("Salami"));
        premiumMeat.add(new Toppings("Roast Beef"));
        premiumMeat.add(new Toppings("Chicken"));
        premiumMeat.add(new Toppings("Bacon"));

        double s = 1.00;
        double m = 2.00;
        double l = 3.00;
        String indent = "   ";
        boolean isFound= false;

        System.out.println("\n         🥩 " + YELLOW + "MEAT TOPPINGS"  + RESET + " 🥩 ");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        for (Toppings t : premiumMeat){
            System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", t.getName(), s, m, l);
        }
        lines();
        while(!isFound) {
            System.out.print("Enter: ");
            String meat = scanner.nextLine();

            for (Toppings t : premiumMeat) {
                if (t.getName().equalsIgnoreCase(meat)) {
                    meatPremium=meat;
                    sandwich.addTopping(new Meat(meatPremium)); // Store the Premium meat on Toppings
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                System.out.println("Invalid input. Please try again");
            }
        }
        lines();

        while (true) {
            System.out.print(GREEN + "Add Extra Meat [Y/N] ? : "  + RESET );
            exMeat = scanner.nextLine().trim().toLowerCase();
            if(exMeat.equals("yes") || exMeat.equals("y")){
                sandwich.addTopping(new Meat(meatPremium, true));
                System.out.print(GREEN + "Extra meat added ✅"  + RESET );
                break;
            }else if(exMeat.equals("n") || exMeat.equals("no")){
                break;
            } else{
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        cheese(sandwich);
    }
    public void cheese(Sandwich<ITopping> sandwich){
        IncludedToppings it = new IncludedToppings(sandwich);
        premiumCheese.clear();
        premiumCheese.add(new Toppings("American"));
        premiumCheese.add(new Toppings("Provolone"));
        premiumCheese.add(new Toppings("Cheddar"));
        premiumCheese.add(new Toppings("Swiss"));

        double s = .75;
        double m = 1.50;
        double l = 2.25;
        boolean isFound = false;
        String indent = "   ";
        System.out.println("\n========= 🧀 " + YELLOW + "CHEESE TOPPINGS"  + RESET + " 🧀 ==========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        for(Toppings c : premiumCheese){
            System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", c.getName(), s, m, l);
        }
        lines();
        while(!isFound) {
            System.out.print("Enter: ");
            String cheese = scanner.nextLine();

            for (Toppings c : premiumCheese) {
                if (c.getName().equalsIgnoreCase(cheese)) {
                    cheesePremium=cheese;
                    sandwich.addTopping(new Cheese(cheesePremium));
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                System.out.println("Invalid input. Please try again");
            }
        }
        lines();
       // System.out.printf("Extra Cheese  |   %.2f | %.2f | %.2f", .30,.60,.90);
        while (true) {
            System.out.print(GREEN + "Add Extra Cheese [Y/N] ? : "  + RESET );
            exCheese = scanner.nextLine().trim().toLowerCase();
            if(exCheese.equals("yes") || exCheese.equals("y")){
                sandwich.addTopping(new Cheese(cheesePremium, true));
                System.out.print(GREEN + "Extra cheese added ✅\n"  + RESET );
                break;
            }else if(exCheese.equals("n") || exCheese.equals("no")){
                break;
            } else{
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        lines();
        it.freeTop(); // Add included sauces, toppings, sides
        lines();
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
        lines();
        sandwich.setToasted(isToasted);
        sandwichOrders.add(sandwich);  // Add fully built sandwich to order
        System.out.println();
        System.out.println(leftIndent + GREEN + "🥖 Sandwich added! 🥖\n" + RESET);

    }

    public void lines(){
        System.out.println("===========================================");
    }


public void addDrinks() {
    List<String> drinks = Arrays.asList("Coke", "Sprite", "Fanta", "Pepsi");
    Map<String, Double> prices = Map.of("S", 2.00, "M", 2.50, "L", 3.00);

    lines();
    System.out.println(YELLOW + "🥤 Available Drinks:" + RESET);
    for (int i = 0; i < drinks.size(); i++) {
        System.out.printf(" %d - %s\n", i + 1, drinks.get(i));
    }
    lines();
    while (true) {
        System.out.print("Enter a number (press 0 when done): ");
        int choice = getIntInput();
        if (choice == 0) break;

        if (choice >= 1 && choice <= drinks.size()) {
            String drink = drinks.get(choice - 1);

            while(true) {
                System.out.print("Select size (S/M/L): ");
                String size = scanner.nextLine().trim().toUpperCase();


                if (prices.containsKey(size)) {
                    drinksOrdered.add(drink + " (" + size + ")");
                    drinksTotal += prices.get(size);
                    System.out.println(GREEN + drink + " " + size + " added. $" + prices.get(size) + RESET);
                    break;
                } else {
                    System.out.println("Invalid size.");
                }
            }

        } else {
            System.out.println("Invalid drink number.");
        }
    }
}

    public void addChips() {
        List<String> chips = Arrays.asList("Pringles", "Doritos", "Fritos", "Tostitos", "Cheetos");
        double chipPrice = 1.50;

        lines();
        System.out.println(YELLOW+ "🍟 Available Chips ($1.50 each):" + RESET);
        for (int i = 0; i < chips.size(); i++) {
            System.out.printf(" %d - %s\n", i + 1, chips.get(i));
        }
        lines();

        while (true) {
            System.out.print("Enter a number (press 0 when done): ");
            int choice = getIntInput();
            if (choice == 0) break;

            if (choice >= 1 && choice <= chips.size()) {
                String chip = chips.get(choice - 1);
                chipsOrdered.add(chip);
                chipsTotal += chipPrice;
                System.out.println(GREEN + chip + " added. $" + chipPrice + RESET);
            } else {
                System.out.println("Invalid chip number.");
            }
        }
    }
    // Handles integer input safely
    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    // Clear current order details
    public void clearOrder() {
        sandwichOrders.clear();
        allReceipts.clear();
        receipt = new StringBuilder();
        grandTotal = 0;
        drinksOrdered.clear();
        chipsOrdered.clear();
        drinksTotal = 0;
        chipsTotal = 0;
    }
    // Check if the order has any items
    public boolean isOrderEmpty() {
        return sandwichOrders.isEmpty() && drinksOrdered.isEmpty() && chipsOrdered.isEmpty();
    }
    // Print the full order summary to console
    public void printReceipts() {
        double sandwichesTotal = 0;
        StringBuilder receipt = new StringBuilder("--- FULL ORDER RECEIPT ---\n");

        int count = 1;
        for (Sandwich<ITopping> s : sandwichOrders) {
            receipt.append("\nSandwich #").append(count++).append(":\n");
            receipt.append(s.getReceipt());
            sandwichesTotal += s.getTotalPrice();
        }

        if (!drinksOrdered.isEmpty()) {
            receipt.append("\n🥤 Drinks:\n");
            for (String d : drinksOrdered) {
                receipt.append("  + ").append(d).append("\n");
            }
            receipt.append("Drinks Total: $").append(String.format("%.2f", drinksTotal)).append("\n");
        }

        if (!chipsOrdered.isEmpty()) {
            receipt.append("\n🍟 Chips:\n");
            for (String c : chipsOrdered) {
                receipt.append("  + ").append(c).append("\n");
            }
            receipt.append("Chips Total: $").append(String.format("%.2f", chipsTotal)).append("\n");
        }

        double grandTotal = sandwichesTotal + drinksTotal + chipsTotal;
        boolean isMember = (!user.getFirstname().contains("GUEST"));

        if (isMember) {
            double discount = grandTotal * 0.15;

            receipt.append("\n").append("Grand Total before discount: ").append(String.format("$%.2f", grandTotal)); // Showing the Grand total before discount
            receipt.append("\n").append(GREEN).append("Member Discount (15%): -$").append(String.format("%.2f", discount)).append(RESET);
            grandTotal -= discount;
        }
        receipt.append("\n").append(YELLOW).append("GRAND TOTAL: $")
                .append(String.format("%.2f", grandTotal))
                .append(RESET).append("\n");

        System.out.println(receipt);
    }
    // Generate the full receipt string for file writing
    public String buildReceipt() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String timestamp = now.format(formatter);
        int orderNumber = new Random().nextInt(90000) + 10000;

        double sandwichesTotal = 0;
        StringBuilder receipt = new StringBuilder("--- FULL ORDER RECEIPT ---\n");

        receipt.append("\n").append(timestamp).append("\n");    // Printing time
        receipt.append("Order #: ").append(orderNumber).append("\n"); // Printing Random Order number
        receipt.append("Customer name: ").append(user.getFirstname().toUpperCase()).append("\n"); // Printing current user firstname
        receipt.append("Email: ").append(user.getEmail().toLowerCase()).append("\n"); // Printing current user email

        int count = 1;
        for (Sandwich<ITopping> s : sandwichOrders) {
            receipt.append("\nSandwich #").append(count++).append(":\n");
            receipt.append(s.getReceipt());
            sandwichesTotal += s.getTotalPrice();
        }

        if (!drinksOrdered.isEmpty()) {
            receipt.append("\n🥤 Drinks:\n");
            for (String d : drinksOrdered) {
                receipt.append("  + ").append(d).append("\n");
            }
            receipt.append("Drinks Total: $").append(String.format("%.2f", drinksTotal)).append("\n");
        }

        if (!chipsOrdered.isEmpty()) {
            receipt.append("\n🍟 Chips:\n");
            for (String c : chipsOrdered) {
                receipt.append("  + ").append(c).append("\n");
            }
            receipt.append("Chips Total: $").append(String.format("%.2f", chipsTotal)).append("\n");
        }
        double grandTotal = sandwichesTotal + drinksTotal + chipsTotal;

        boolean isMember = (!user.getFirstname().contains("GUEST")); // Check if the user is a member

        if (isMember) {
            double discount = grandTotal * 0.15; // If member, applying 15% off
            receipt.append("\n").append("Grand Total before discount: ").append(String.format("$%.2f", grandTotal)); // Showing the Grand total before discount
            receipt.append("\nMember Discount (15%): -$").append(String.format("%.2f", discount));
            grandTotal -= discount;
        }

        receipt.append("\n").append("GRAND TOTAL: $")
                .append(String.format("%.2f", grandTotal)).append("\n");

        return receipt.toString();  // Used by Receipt.writeReceipt()
    }


}
