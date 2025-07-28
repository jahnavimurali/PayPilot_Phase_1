package com.paypilot.util;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.paypilot.model.Payment;

public class PaymentBackup {

    public static void backupPayments(List<Payment> payments, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Payment payment : payments) {
                String timestamp = LocalDateTime.now().format(timestampFormatter);
                String formattedDate = payment.getPaymentDate().format(dateFormatter);

                String line = String.format(
                        "Timestamp: %s | PaymentID: %d | BillID: %d | Date: %s | Amount: %.2f",
                        timestamp,
                        payment.getPaymentId(),
                        payment.getBillId(),
                        formattedDate,
                        payment.getAmountPaid()
                );

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing backup: " + e.getMessage());
        }
    }
}
