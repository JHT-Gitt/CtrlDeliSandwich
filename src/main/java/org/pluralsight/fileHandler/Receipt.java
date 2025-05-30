package org.pluralsight.fileHandler;



import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
    private static final String file = "src/main/resources/receipt.csv";


    public static void writeReceipt(String receipt) {
        try (FileWriter writer = new FileWriter(file, true)) { // Appending the file
            writer.write(receipt);
            writer.write("\n------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error writing to receipt file: " + e.getMessage());
        }
    }
}
