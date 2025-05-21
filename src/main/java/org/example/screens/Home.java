package org.example.screens;

import org.example.fileHandler.CusterFileHandler;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Home {
    Scanner scanner = new Scanner(System.in);
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    Menu menu = new Menu();

    public void display(){
        String enter;
    banner();
        System.out.println(GREEN + "💥%10 member off💥" + RESET);
        System.out.println(" L - Log-in     \n S - Sign-up");
        System.out.println(" G - Continue as Guest \n X - Exit");
        System.out.println("===================================");
        while (true) {
            try {
        System.out.print("Enter: ");
        enter = scanner.next().toUpperCase();
        switch (enter){
            case "L" -> logIn();
            case "S" -> signUp();
            case "G" -> guest();
            case "X" -> {
                System.out.println("Goodbye");
                System.exit(0);
            }
            default -> System.out.println("Invalid input. Try again");
        }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    public void banner(){
    System.out.println("===================================");
    System.out.println("             Welcome to");
    System.out.println(YELLOW +  "                J's");
    System.out.println("   🥪  🥪  CNTRL + DELI  🥪  🥪" + RESET );
    System.out.println("             Sandwiches");
    System.out.println("===================================");
    }
    public void logIn(){
        CusterFileHandler login = new CusterFileHandler();

        System.out.println("=============Log-In===============");
        while(true) {
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            if (login.member(email, password)) {
                menu.order();
                break;
            } else {
                System.out.println("Invalid email or password");
            }
        }

    }
    public void signUp(){
        File cust_file = new File("src/main/resources/customer.csv");
        System.out.println("============Sign-Up================");
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter phone number: ");
        String phone = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        String newCustomer = String.join("|", firstName, lastName, email, phone, password);
        // Append to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cust_file, true))) {
            writer.newLine();
            writer.write(newCustomer);
            System.out.println(GREEN + "Sign-up successful!" + RESET);


        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        display();

    }
    public void loginAfterSign(){
        System.out.println("===================================");
        System.out.println(" L - Log-in ");
        System.out.println(" E - Exit");
        System.out.println("===================================");
        while(true){
            System.out.print("Enter: ");
            String enter = scanner.next().toUpperCase();

            switch (enter){
                case "L" -> logIn();
                case "E" -> {
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input! Try again");
            }


        }
    }
    private void guest() {

        Random r = new Random();
        int guestNum = 10000 + r.nextInt(90000);
        String g = "Guest";
        System.out.println(YELLOW + g+guestNum + RESET);
        menu.order();

    }
}
