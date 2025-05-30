package org.pluralsight.screens;

import org.pluralsight.Models.Order;
import org.pluralsight.customer.Login;
import org.pluralsight.fileHandler.Receipt;

import java.util.Scanner;

public class OrderScreen {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    private final Login user; // Logged-in or guest user
    private final Order os;  // Order handler for current session
    public String indent="              "; // Indenter
    public String indent1="            "; // Indenter

    Scanner scanner = new Scanner(System.in);
    // Constructor accepts user info and links it to the order object
    public OrderScreen(Login user) {
        this.user = user;
        this.os = new Order(user);
    }
    // Main home menu after user login or guest access
    public void order(Login user){

        System.out.println("=============="+ YELLOW + "HOME SCREEN" +RESET + "==================");
        System.out.println(indent + " 1 - New Order");
        System.out.println(indent + " 0 - Exit");
        os.lines();// Print divider line
        // Menu loop
        while(true) {
        try {
        System.out.print("Enter: ");
        int enter = scanner.nextInt();

            switch (enter){
                case 0 ->{
                    System.out.println("Goodbye and Have a great day 👋");
                    System.exit(0);  // Exit program
                    //return;
                }
                case 1 -> orderScreen();  // Go to order screen
                default -> System.out.println("Invalid input. Try again");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear scanner buffer
        }
        }
    }
    //Placeholder for signature sandwich tho I got no time to do it *sniff *womp *womp
    private void signatureSandwich() {
        System.out.println("Still empty");

    }
    // The core order flow screen with options to add items or checkout
    private void orderScreen() {

        System.out.println("=============="+ YELLOW + "ORDER SCREEN" +RESET + "=================");
        System.out.println(" 1 - Add Customize Sandwich");
        System.out.println(" 2 - Add Drink");
        System.out.println(" 3 - Add Chips");
        System.out.println(" 4 - View Signature Sandwich");
        System.out.println(" 5 - Checkout");
        System.out.println(" 0 - Cancel Order");
        os.lines();
        // Loop for order options
        while(true) {
            try {
                System.out.print("Enter: ");
                int enter = scanner.nextInt();

                switch (enter){
                    case 0 ->{
                        System.out.print("Do you want to cancel this session? (Y/N): "); // Ask for confirmation before canceling
                        String confirm = scanner.next().toUpperCase();
                        if (confirm.equals("Y") || confirm.equals("YES")) {
                            os.clearOrder();  // <--- this clears everything
                            System.out.println("Order cancelled.");
                            order(user);      // back to Home Screen
                        } else {
                            System.out.println("Returning to Order Screen...");
                            orderScreen();
                        }
                    }
                    case 1 -> {
                        os.addSandwich();  // Add custom sandwich
                        orderScreen(); // Return back to this screen after adding
                    }
                    case 2 -> {
                        os.addDrinks(); // Add drink to order
                        orderScreen(); // Return back to this screen after adding
                    }
                    case 3 -> {
                        os.addChips();  // Add chips to order
                        orderScreen(); // Return back to this screen after adding
                    }
                    case 4 -> {
                        System.out.println("Feature not Added yet"); // Feature to be implemented, I hope, maybe or one day 😿
                        orderScreen();
                    }
                    case 5 -> {
                        // If no items in order, order first :P
                        if (os.isOrderEmpty()) {
                            System.out.println(indent1 +RED +"⚠️Order is empty.⚠️\n" + RESET);
                            orderScreen();
                            return;
                        }

                        os.printReceipts(); // Show order summary
                        // Confirmation loop before finalizing order
                        while (true) {
                            System.out.print("\n📌 Confirm order? \n✅ Y - Confirm Order \n❌ C - Cancel order \n⬅️ R - Return\nEnter: ");
                            String input = scanner.next().trim().toUpperCase();

                            switch (input) {
                                case "Y", "YES" -> {
                                    String fullReceipt = os.buildReceipt(); // Build final receipt
                                    Receipt.writeReceipt(fullReceipt);      // Save to file
                                    os.clearOrder();                        // Clear current session
                                    System.out.println(GREEN + "Thank you for your purchased ! \nYour order has been confirmed and saved." + RESET); // Checkout message
                                    order(user); // back to home
                                    return;
                                }
                                case "C", "CANCEL" -> {
                                    os.clearOrder();
                                    System.out.println("Order cancelled.");
                                    order(user);
                                    return;
                                }
                                case "R", "RETURN" -> {
                                    System.out.println("Returning to Order Screen...");
                                    orderScreen();
                                    return;
                                }
                                default -> System.out.println("Invalid input. Please enter Y, C, or R.");
                            }
                        }
                    }
                    default -> System.out.println("Invalid input. Try again");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }

    }




}
