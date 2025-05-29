package org.pluralsight.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.pluralsight.Interface.Extras;
import org.pluralsight.Interface.I_Topping;
import org.pluralsight.customer.Login;
import org.pluralsight.customer.Sandwich;
import org.pluralsight.fileHandler.Receipt;
import org.pluralsight.screens.OrderScreen;

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
    public String receipt;
    public double total = 0;
    public double grandTotal = 0;

    List<String> allReceipts = new ArrayList<>();
    List<Topping> meatTop = new ArrayList<>();
    List<Topping> cheeseTop = new ArrayList<>();
    Sandwich<I_Topping> sandwich = new Sandwich<>(size);
    private List<Sandwich<?>> sandwiches = new ArrayList<>();
    private Login user;

    OrderScreen os;
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
        lines();
        sandwich.setSize(size);
        //System.out.println(sandwich.getBasePrice());

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
        
        lines();
        //System.out.println(sandwich.getMeatPremium());
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
        lines();
        //System.out.println(sandwich.getCheesePremium());
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

            if (exMeat.equals("yes") ||
                    exMeat.equals("y") ) {
                lines();
                total = sandwich.getExtraMeat();
                System.out.println("Extra meat added !");
                exMeat = "YES";
                //System.out.println(total);
                break;
            }else if(exMeat.equals("n") || exMeat.equals("no")){
                exMeat = "NO";
                       break;
            } else{
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }

        }

//        if (exMeat.equals("yes") || exMeat.equals("y")) {
//            sandwich.addTopping(new MeatTopping(meatPremium, true));
//        }
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

            if (exCheese.equals("yes") ||
                   exCheese.equals("y")) {
                lines();
                total = sandwich.getExtraCheese() ;
                System.out.println("Extra cheese added !");
                 exCheese = "YES";
                //System.out.println(total);
                break;

            }else if(exCheese.equals("n") || exCheese.equals("no")){
                exCheese = "NO";
                            break;
            }else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

//        if (exCheese.equals("yes") || exCheese.equals("y")) {
//            sandwich.addTopping(new CheeseTopping(cheesePremium, true));
//        }
        lines();


    }
    public void printReceipt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String timestamp = now.format(formatter);
        int orderNumber = new Random().nextInt(90000) + 10000;
        double totalPrice = total + sandwich.totalPrice();

       receipt = "\n-----------\n" +
                "Name: " + user.getFirstname() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Order #: " + orderNumber + "\n" +
                "Time: " + timestamp + "\n" +
                "Bread Type: " + breadType.toUpperCase() + "\n" +
                "Size: " + size + "\n" +
                "Toasted: " + isToasted.toUpperCase() + "\n" +
                "Meat Premium: " + meatPremium.toUpperCase() + "\n" +
                "Extra Meat: " + exMeat.toUpperCase() + "\n" +
                "Cheese Premium: " + cheesePremium.toUpperCase() + "\n" +
                "Extra Cheese: " + exCheese.toUpperCase() + "\n\n"     +
                 "Total Price: " + totalPrice
       ;
      allReceipts.add(receipt); // optional, if you want to preserve history
        grandTotal += totalPrice;
    }

//    public void printAllReceipts() {
//        System.out.println("\n========= ALL RECEIPTS =========");
//        for (String receipt : allReceipts) {
//            System.out.println(receipt);
//            System.out.println("--------------------------------");
//            Receipt.writeReceipt(receipt);
//        }
//
//    }
    public boolean checkout() {
        int count = 1;
        if (allReceipts.isEmpty()) {
            System.out.println("⚠️ You haven't added any sandwich yet. ⚠️");
            return false;
        }

        System.out.println("\n🧾 Your Order Summary:");
        for (String receipt : allReceipts) {
            System.out.println("Sandwich #" + count++);
            System.out.println(receipt);
            System.out.println("-----------------------------");
        }
        System.out.println(" 💵 Grand Total: " + grandTotal);

        while(true) {
            System.out.print("\n📌 Confirm order?\n ✅ Y - Confirm\n ❌ N - Cancel Order\nEnter: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Y") || input.equals("YES")) {
                for (String receipt : allReceipts) {
                    Receipt.writeReceipt(receipt); // Save all receipts
                }
                clearOrder();
                System.out.println("✅ Order confirmed and saved.✅");
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                clearOrder();
                System.out.println("❌ Order canceled. ❌");
                return false;
            }else{
                System.out.println("❌ Invalid output. Try again ❌");
            }
        }
    }
    
    public void clearOrder() {
        sandwiches.clear();
        allReceipts.clear();
        total = 0;
        receipt = "";
        grandTotal = 0;
    }

}
