package com.paypilot.util;

package com.junit;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class PaymentBackup {

    public static void backupPayments(List<Payment> payments, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Payment payment : payments) {
                String timestamp = LocalDateTime.now().format(formatter);

                String line = String.format("Timestamp: %s | PaymentID: %d | BillID: %d | Date: %s | Amount: %.2f |  Mode: %s",
                        timestamp,
                        payment.getPaymentId(),
                        payment.getBillId(),
                        payment.getPaymentDate(),
                        payment.getAmountPaid(),
                        payment.getMode());

                writer.write(line);
                writer.newLine();
                
                System.out.println("data written");
            }

        } catch (IOException e) {
            System.out.println("Error writing backup: " + e.getMessage());
        }
    }
}
