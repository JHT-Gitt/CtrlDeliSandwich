package org.pluralsight.screens;

import org.pluralsight.Model.Order;
import org.pluralsight.UI.Orders;
import org.pluralsight.customer.Login;
import org.pluralsight.fileHandler.CustomerFileHandler;
import org.pluralsight.fileHandler.Receipt;

import java.util.Scanner;

public class OrderScreen {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    private final Login user;
    private final Order om;
    private final Orders os;
    CustomerFileHandler cf = new CustomerFileHandler();


    Scanner scanner = new Scanner(System.in);
    public OrderScreen(Login user) {
        this.user = user;
        this.om = new Order(user);
        this.os = new Orders(user);
    }

    public void order(Login user){

        System.out.println("=========="+ YELLOW + "HOME SCREEN" +RESET + "==============");
        System.out.println(" 1 - New Order");
        System.out.println(" 0 - Exit");
        System.out.println("===================================");
        while(true) {
        try {
        System.out.print("Enter: ");
        int enter = scanner.nextInt();

            switch (enter){
                case 0 ->{
                    System.out.println("Goodbye and Have a great day 👋");
                    System.exit(0);
                    //return;
                }
                case 1 -> orderScreen();
                default -> System.out.println("Invalid input. Try again");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        }
    }

    private void signatureSandwich() {
        System.out.println("Still empty");

    }
    private void orderScreen() {


        System.out.println("=========="+ YELLOW + "ORDER SCREEN" +RESET + "=============");
        System.out.println(" 1 - Add Customize Sandwich");
        System.out.println(" 2 - Add Drink");
        System.out.println(" 3 - Add Chips");
        System.out.println(" 4 - View Signature Sandwich");
        System.out.println(" 5 - Checkout");
        System.out.println(" 0 - Cancel Order");
        System.out.println("===================================");
        while(true) {
            try {
                System.out.print("Enter: ");
                int enter = scanner.nextInt();

                switch (enter){
                    case 0 ->{
                        System.out.print("Are you sure you want to cancel this order? (Y/N): ");
                        String confirm = scanner.next().toUpperCase();
                        if (confirm.equals("Y") || confirm.equals("YES")) {
                            os.clearOrder();  // <--- this clears everything
                            System.out.println("Order cancelled.");
                            order(user);      // back to Home Screen
                        } else {
                            System.out.println("Returning to Order Screen...");
                            orderScreen();
                        }
//                        System.out.print("Are you sure you want to cancel this order? (Y/N): ");
//                        String confirm = scanner.next().toUpperCase();
//                        if (confirm.equals("Y") || confirm.equals("YES")) {
//                            order(user);
//                        } else {
//                            System.out.println("Returning to Order Screen...");
//                        }
                    }
                    case 1 -> {
                        os.addSandwich();
                        orderScreen();
                    }
                    case 2 -> {
                        os.addDrinks();
                        orderScreen();
                    }
                    case 3 -> {
                        os.addChips();
                        orderScreen();
                    }
                    case 4 -> {
                        System.out.println("Feature not Added yet");
                        orderScreen();
                    }
                    case 5 -> {
                        if (os.isOrderEmpty()) {
                            System.out.println("Your order is empty. Please add sandwiches, drinks, or chips before checking out.");                            orderScreen();
                            return;
                        }

                        os.printReceipts();

                        while (true) {
                            System.out.print("\nConfirm order? (Y to Confirm / C to Cancel / R to Return): ");
                            String input = scanner.next().trim().toUpperCase();

                            switch (input) {
                                case "Y", "YES" -> {
                                    String fullReceipt = os.buildReceipt(); // You’ll define this next
                                    Receipt.writeReceipt(fullReceipt);      // Writes to file
                                    os.clearOrder();                         // Clears data
                                    System.out.println(GREEN + "Thank you! Your order has been confirmed and saved." + RESET);
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
                scanner.nextLine();
            }
        }

    }




}
