package org.pluralsight.UI;

import org.pluralsight.customer.Login;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Orders {

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
    //public String receipt;
    public StringBuilder receipt;

    private Login user;

    private final List<Sand<ITopping>> sandwichOrders = new ArrayList<>();
    Sand<ITopping> sandwich = new Sand<>(size);
    List<Toppings> premiumMeat = new ArrayList<>();
    List<Toppings> premiumCheese = new ArrayList<>();
    List<String> allReceipts = new ArrayList<>();
    IncludedToppings it = new IncludedToppings();

    public Orders(Login user){
        this.user = user;
    }

    public void addSandwich(){
        int enter;
        lines();
        System.out.println("NEW");
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
        breadSize();
    }

    public void breadSize(){
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
        lines();
        sandwich.setSize(size);

        meat();
    }
    public void meat(){
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

        System.out.println("\n========== 🥩 " + YELLOW + "MEAT TOPPINGS"  + RESET + " 🥩 ===========");
        System.out.printf(indent + YELLOW + "%-12s|   %-5s|   %-5s|   %-5s\n" + RESET, "Type", "4\"", "8\"","12\"");
        for (Toppings t : premiumMeat){
            System.out.printf(indent + "%-12s| $%.2f  | $%.2f  | $%.2f\n", t.getName(), s, m, l);
        }
        while(!isFound) {
            System.out.print("Enter: ");
            String meat = scanner.nextLine();

            for (Toppings t : premiumMeat) {
                if (t.getName().equalsIgnoreCase(meat)) {
                    meatPremium=meat;
                    sandwich.addTopping(new Meat(meatPremium));
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                System.out.println("Invalid input. Please try again");
            }
        }
        lines();
        System.out.printf("Extra Meat  |   %.2f | %.2f | %.2f", .50,1.00,1.50);
        while (true) {
            System.out.print(GREEN + "\nAdd Extra Meat [Y/N] ? : "  + RESET );
            exMeat = scanner.nextLine().trim().toLowerCase();
            if(exMeat.equals("yes") || exMeat.equals("y")){
                sandwich.addTopping(new Meat(meatPremium, true));
                break;
            }else if(exMeat.equals("n") || exMeat.equals("no")){
                break;
            } else{
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        cheese();
    }
    public void cheese(){
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
        System.out.printf("Extra Cheese  |   %.2f | %.2f | %.2f", .30,.60,.90);
        while (true) {
            System.out.print(GREEN + "\nAdd Extra Cheese [Y/N] ? : "  + RESET );
            exCheese = scanner.nextLine().trim().toLowerCase();
            if(exCheese.equals("yes") || exCheese.equals("y")){
                sandwich.addTopping(new Cheese(cheesePremium, true));
                break;
            }else if(exCheese.equals("n") || exCheese.equals("no")){
                break;
            } else{
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        it.freeTop();
        //free();

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
        sandwichOrders.add(sandwich);
        System.out.println("Sandwich Added!\n");
        //printReceipt();
        printReceipts();

    }
    public void free(){

        String[] regularToppings = {"lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles"};
        System.out.println("\nChoose regular toppings (enter numbers separated by commas):");
        for (int i = 0; i < regularToppings.length; i++) {
            System.out.println((i + 1) + ". " + regularToppings[i]);
        }
        System.out.print("Your choices: ");
        String[] toppingChoices = scanner.nextLine().split(",");
        List<String> selectedToppings = Arrays.stream(toppingChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= regularToppings.length)
                .map(i -> regularToppings[i - 1])
                .distinct()
                .toList();

        for (String topping : selectedToppings) {
            sandwich.addTopping(new BasicTopping(topping));
        }

// Sauces Selection
        String[] sauces = {"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"};
        System.out.println("\nChoose sauces (enter numbers separated by commas):");
        for (int i = 0; i < sauces.length; i++) {
            System.out.println((i + 1) + ". " + sauces[i]);
        }
        System.out.print("Your choices: ");
        String[] sauceChoices = scanner.nextLine().split(",");
        List<String> selectedSauces = Arrays.stream(sauceChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= sauces.length)
                .map(i -> sauces[i - 1])
                .distinct()
                .toList();

        for (String sauce : selectedSauces) {
            sandwich.addTopping(new BasicTopping(sauce));
        }

// Sides Selection
        String[] sides = {"au jus", "sauce"};
        System.out.println("\nChoose sides (enter numbers separated by commas):");
        for (int i = 0; i < sides.length; i++) {
            System.out.println((i + 1) + ". " + sides[i]);
        }
        System.out.print("Your choices: ");
        String[] sideChoices = scanner.nextLine().split(",");
        List<String> selectedSides = Arrays.stream(sideChoices)
                .map(String::trim)
                .filter(choice -> choice.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(i -> i >= 1 && i <= sides.length)
                .map(i -> sides[i - 1])
                .distinct()
                .toList();

        for (String side : selectedSides) {
            sandwich.addTopping(new BasicTopping(side));
        }

    }

    public void lines(){
        System.out.println("===========================================");
    }
    public void printReceipts(){
        double grandTotal = 0;
        StringBuilder receipt = new StringBuilder("--- FULL RECEIPT ---\n");
        int count =1;
        for(Sand<ITopping> s : sandwichOrders){
            receipt.append("Sandwich #").append(count++).append(":\n");
            receipt.append(sandwich.getReceipt()).append("\n");
            grandTotal += sandwich.getTotalPrice();
        }
        receipt.append("GRAND TOTAL: $").append(String.format("%.2f", grandTotal)).append("\n");

        System.out.println(receipt);

    }
//    public void printReceipt(){
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
//        String timestamp = now.format(formatter);
//        int orderNumber = new Random().nextInt(90000) + 10000;
//        receipt = "\n-----------\n" +
//                "Name: " + user.getFirstname() + "\n" +
//                "Email: " + user.getEmail() + "\n" +
//                "Order #: " + orderNumber + "\n" +
//                "Time: " + timestamp + "\n" +
//                "Size: " + size + "\n" +
//                "Toasted: " + isToasted + "\n" +
//                "Meat Premium: " + meatPremium.toUpperCase() + "\n" +
//                "Extra Meat: " + exMeat.toUpperCase() + "\n" +
//                "Cheese Premium: " + cheesePremium.toUpperCase() + "\n" +
//                "Extra Cheese: " + exCheese.toUpperCase() + "\n\n"     +
//                "Total Price: " + sandwich.getTotalPrice()
//
//
//        ;
//        System.out.println(receipt);
//    }










}
