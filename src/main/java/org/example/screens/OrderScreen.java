package org.example.screens;

import org.example.customer.Login;
import org.example.fileHandler.CustomerFileHandler;

import java.util.Scanner;

public class OrderScreen {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    private final Login user;
    private final Order om;
    CustomerFileHandler cf = new CustomerFileHandler();


    Scanner scanner = new Scanner(System.in);
    public OrderScreen(Login user) {
        this.user = user;
        this.om = new Order(user);
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
                        System.out.println("Goodbye and Have a great day 👋");
                        System.exit(0);
                    }
                    case 1 -> om.addSandwich();
                    default -> System.out.println("Invalid input. Try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

    }


}
