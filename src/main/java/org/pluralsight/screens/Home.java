package org.pluralsight.screens;

import org.pluralsight.customer.Login;
import org.pluralsight.fileHandler.CustomerFileHandler;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Home {

    Scanner scanner = new Scanner(System.in);
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public String indent="        ";

    CustomerFileHandler cf = new CustomerFileHandler();

    public void display(){
        String enter;
        banner();
        System.out.println(indent + GREEN + "    💥%15 member off💥" + RESET);
        System.out.println(indent + " L - Log-in      S - Sign-up");
        System.out.println(indent + " G - Guest       X - Exit");
        lines();
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

        lines();
        System.out.printf("%26s\n","Welcome to");
        System.out.printf("%28s\n",YELLOW +  "J's");
        System.out.println("       🥪  🥪  CNTRL + DELI  🥪  🥪" + RESET );
        System.out.printf("%26s\n","Sandwiches");
        lines();
    }
    public void logIn() {
        System.out.println("==================" + YELLOW + "Log-In" + RESET + "===================");
        while (true) {
            System.out.print("Email   : ");
            String email = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            Login user = cf.loginUser(email, password);
            if (user != null) {
                OrderScreen menu = new OrderScreen(user);
                menu.order(user);
                break;
            } else {
                System.out.println("Invalid email or password");
            }
        }
    }

    public void signUp(){
        File cust_file = new File("src/main/resources/customer.csv");
        String email;
        System.out.println("============"+ YELLOW + "Sign-In" +RESET + "================");
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        while (true) {
            System.out.print("Email: ");
            email = scanner.next();
            if (cf.checkEmail(email)) {
                System.out.println("That email is already registered. Try another.");
            } else {
                break;
            }
        }
        System.out.print("Enter phone number: ");
        String phone = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        String newCustomer = String.join("|", firstName, lastName, email, phone, password);
        // Append to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cust_file, true))) {
            if (cust_file.length() > 0) {
                writer.newLine();
            }
            writer.write(newCustomer);
            lines();
            System.out.printf(GREEN + "%30s\n", "✅Sign-In Successful !✅" + RESET);
            cf = new CustomerFileHandler();  // RELOADS customer data
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        loginAfterSign();

    }
    public void loginAfterSign(){

        lines();
        System.out.println(" H - Home ");
        System.out.println(" L - Log-in ");
        System.out.println(" E - Exit");
        lines();
        while(true){
            System.out.print("Enter: ");
            String enter = scanner.next().toUpperCase();

            switch (enter){
                case "H" -> display();
                case "L" -> logIn();
                case "E" -> {
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input! Try again");
            }


        }
    }
    public void guest() {

        Random r = new Random();
        int guestNum = 10000 + r.nextInt(90000);
        lines();
        String g = "GUEST" + guestNum;
        System.out.printf( GREEN + "%45s\n", "Hello " + YELLOW + g.toUpperCase() + RESET +  GREEN + " !" );
        System.out.printf("%36s\n", "How can I help you ?" + RESET );

        Login guestLogin = new Login(g, "N/A");
        OrderScreen menu = new OrderScreen(guestLogin);
        menu.order(guestLogin);


    }
    public void lines(){
        System.out.println("===========================================");

    }

}