package com.paypilot.util;

import java.time.LocalDate;

import com.paypilot.exception.InvalidPaymentDateException;
import com.paypilot.model.Bill;
import com.paypilot.model.Payment;

public class PaymentDateValidator {
	public static void validatePaymentDate(Bill bill, Payment payment) throws InvalidPaymentDateException{
		if (payment.getPaymentDate().isBefore(bill.getDueDate())) {
            throw new InvalidPaymentDateException("Payment date cannot be before the due date.");
        } else {
            System.out.println("Payment date is valid." + payment.getPaymentDate());
        }
	}
}
