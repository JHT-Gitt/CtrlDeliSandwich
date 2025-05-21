package org.example.screens;

import org.example.fileHandler.CusterFileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Home {
    Scanner scanner = new Scanner(System.in);
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

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
//       File file = new File("src/main/resources/customer.csv");
//
//        try (Scanner scan = new Scanner(file)) {
//            if (scan.hasNextLine()) {
//                scan.nextLine(); // Skip the header
//            }
//            while (scan.hasNextLine()) {
//                System.out.println(scan.nextLine()); // Print remaining lines
//            }
//       } catch (FileNotFoundException e) {
//           System.out.println("No file found");
//       }
        CusterFileHandler login = new CusterFileHandler();


        System.out.println("===Log in===");
        while(true) {
            System.out.print("Email: ");
            String email = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            if (login.member(email, password)) {
                System.out.println("Login Successful !");
                break;
            } else {
                System.out.println("Invalid email or password");
            }
        }

    }
    public void signUp(){




    }
    private void guest() {
        //System.out.println("Its still empty");
        Random r = new Random();
        int guestNum = 10000 + r.nextInt(90000);
        String g = "Guest";
        System.out.println(YELLOW + g+guestNum + RESET);

    }
}
