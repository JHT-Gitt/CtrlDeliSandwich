package org.example.fileHandler;


import org.example.screens.Home;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
    private Home home;

    public Receipt(Home home) {
        this.home = home;
    }

    public void printReceipt() {
        String guestName = home.getGuest;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/receipt.csv", true))) {
            writer.write("Receipt for " + guestName);
            writer.newLine();
            // ... more receipt details
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}
