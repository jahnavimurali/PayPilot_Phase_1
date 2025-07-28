package com.paypilot.util;

import java.io.*;
import java.time.*;
import java.util.*;

import com.paypilot.model.Payment;

public class PaymentBackup {

    public static void backupPayments(List<Payment> payments, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Payment payment : payments) {
                String timestamp = LocalDateTime.now().format(formatter);

                String line = String.format("Timestamp: %s | PaymentID: %d | BillID: %d | Date: %s | Amount: %.2f | Status: %s | Mode: %s",
                        timestamp,
                        payment.getPaymentId(),
                        payment.getBillId(),
                        payment.getPaymentDate(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getMode());

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing backup: " + e.getMessage());
        }
    }
}
