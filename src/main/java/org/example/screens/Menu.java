package org.example.screens;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void order(){

        System.out.println("===================================");
        System.out.println(" 1 - New Order");
        System.out.println(" 2 - Signature Sandwiches");
        System.out.println(" 0 - Exit");
        System.out.println("===================================");
        while(true) {
        try {
        System.out.print("Enter: ");
        int enter = scanner.nextInt();

            switch (enter){
                case 0 ->{
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                case 1 -> orderScreen();
                case 2 -> signatureSandwich();
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
        System.out.println("Still empty");

    }


}
