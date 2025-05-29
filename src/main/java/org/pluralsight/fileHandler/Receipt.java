package org.pluralsight.fileHandler;



import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
    private static final String FILE_PATH = "src/main/resources/receipt.csv";


    public static void writeReceipt(String receipt) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) { // `true` = append mode
            writer.write(receipt);
            writer.write("\n------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error writing to receipt file: " + e.getMessage());
        }
    }
}
