package org.pluralsight.fileHandler;


import org.pluralsight.customer.Login;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class CustomerFileHandler {
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    private static final String cust_file = "src/main/resources/customer.csv";


    public Login loginUser(String email, String password) {

        try (BufferedReader reader = new BufferedReader(new FileReader(cust_file))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] log = line.split("\\|");
                String fileFirstname = log[0];
                String fileLastname = log[1];
                String fileEmail = log[2];
                String filePhoneNumber = log[3];
                String filePassword = log[4];

                if (fileEmail.equalsIgnoreCase(email) && filePassword.equalsIgnoreCase(password)) {
                    System.out.println("===================================");
                    System.out.printf(GREEN + "%30s\n", "✅ Login Successful! ✅" + RESET);
                    System.out.println("===================================");
                    System.out.printf(GREEN + "%35s\n", "Hello " + YELLOW + fileFirstname.toUpperCase() + RESET + GREEN + "!");
                    System.out.printf("%30s\n", "How can I help you?" + RESET);

                    return new Login(fileFirstname, fileEmail);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null; // login failed
    }

    public boolean checkEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cust_file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] log = line.split("\\|");
                if (log.length >= 3) {
                    String fileEmail = log[2];
                    if (fileEmail.equalsIgnoreCase(email)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking email: " + e.getMessage());
        }
        return false;
    }

}