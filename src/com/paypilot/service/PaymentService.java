package com.paypilot.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.paypilot.exception.InvalidPaymentDateException;
import com.paypilot.model.Bill;
import com.paypilot.model.Payment;

public class PaymentService {
    // Singleton class design patterN
    private static PaymentService inst=null;
	// Function to get instance of PaymentService
	public static PaymentService getInstance() {
		if(inst  == null) {
			inst=new PaymentService();
		}
		return inst;
	}

    // Get the Map with key as payment mode and value as number of payments using that payment mode.
	public  Map<String,Long> getModeCount(List<Payment> payments){
		
		Map<String, Long> modeCount=payments.stream().collect(Collectors.groupingBy(Payment::getMode,Collectors.counting()));
		
		return modeCount;
	}

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

	public static void validatePaymentDate(Bill bill, Payment payment) throws InvalidPaymentDateException {
		if (payment.getPaymentDate().isBefore(bill.getDueDate())) {
			throw new InvalidPaymentDateException("Payment date cannot be before the due date.");
		} else {
			System.out.println("Payment date is valid." + payment.getPaymentDate());
		}
	}
}
