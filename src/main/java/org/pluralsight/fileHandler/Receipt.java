package org.pluralsight.fileHandler;



import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Receipt {
    private static final String file = "src/main/resources/receipt.csv";


    public static void writeReceipt(String receipt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);


        String fileName = "src/main/resources/receipt_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) { // No append mode — new file each time
            writer.write(receipt);
            writer.write("\n------------------------------\n");
        } catch (IOException e) {
            System.out.println("❌ Error writing to receipt file: " + e.getMessage());
        }
//        try (FileWriter writer = new FileWriter(file, true)) { // Appending the file
//            writer.write(receipt);
//            writer.write("\n------------------------------\n");
//        } catch (IOException e) {
//            System.out.println("Error writing to receipt file: " + e.getMessage());
//        }
    }
}
