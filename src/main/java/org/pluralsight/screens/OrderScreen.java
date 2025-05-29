package org.pluralsight.screens;

import org.pluralsight.Model.Order;
import org.pluralsight.UI.Orders;
import org.pluralsight.customer.Login;
import org.pluralsight.fileHandler.CustomerFileHandler;

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
                            om.clearOrder();     // Clear sandwich list
                            order(user);             // Return to "Home Screen" menu, still logged in
                        } else {
                            System.out.println("Returning to Order Screen...");
                        }
                    }
                    case 1 -> {
                        os.addSandwich();
                        om.addSandwich();
                        orderScreen();
                    }
                    case 5 -> {
                        boolean success = om.checkout();
                        if (success) {
                            order(user); // go back to home screen
                        } else {
                            orderScreen(); // stay on order screen if nothing to checkout
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
