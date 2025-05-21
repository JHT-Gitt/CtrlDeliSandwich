package org.example.fileHandler;

import org.example.customer.Login;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class CusterFileHandler {
    private static final String cust_file = "src/main/resources/customer.csv";

    public boolean member(String email, String password){
    try(BufferedReader reader = new BufferedReader(new FileReader(cust_file))){
        reader.readLine();
        String line;
        while ((line = reader.readLine()) !=null){
            String[] log = line.split("\\|");
            String fileFirstname = log[0];
            String fileLastname = log[1];
            String fileEmail = log[2];
            String filePhoneNumber = log[3];
            String filePassword = log[4];

            if(fileEmail.equalsIgnoreCase(email) && filePassword.equalsIgnoreCase(password)){
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
        return false;
    }


}
