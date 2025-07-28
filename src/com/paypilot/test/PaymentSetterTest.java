package com.paypilot.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.paypilot.model.Payment;

class PaymentSetterTest {

	private Payment payment;
	
	@BeforeEach
	void setUp() {
		payment=new Payment(0, 0, 0, null, null);
	}

	@Test
	//Check paymentId setter
	void testSetPaymentId() {
		int paymentId=12345;
		payment.setPaymentId(paymentId);
		System.out.println(payment.getPaymentId());
		assertEquals(paymentId, payment.getPaymentId(),"paymentId not set");
	}
	
	@Test
	//Check billId setter
	void testSetBillId() {
		int billId=12345;
		payment.setBillId(billId);
		assertEquals(billId, payment.getBillId(),"billId not set");
	}
	
	@Test
	//Check if amount can be set to positive value
	void testSetAmountPaid() {
		double amountPaid=12345.83;
		payment.setAmountPaid(amountPaid);
		assertEquals(amountPaid, payment.getAmountPaid(),0.001,"Amount not set");
	}
	
	@Test
	//Check if random date can be set in paymentDate
	void testSetRandomPaymentDate() {
		LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.now();
        long days = end.toEpochDay() - start.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = start.plusDays(randomDay);

        payment.setPaymentDate(randomDate);
        assertEquals(randomDate, payment.getPaymentDate(),"Random paymentDate not set");
	}
	
	@Test
	//Check if random date from future can be set in paymentDate
	void testSetFuturePaymentDate() {
		LocalDate start = LocalDate.now().plusDays(1);
	    LocalDate end = LocalDate.now().plusYears(10);
	    long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay() + 1);
	    LocalDate randomFutureDate = LocalDate.ofEpochDay(randomDay);

	    
	    payment.setPaymentDate(randomFutureDate);
	    assertTrue(randomFutureDate.isAfter(LocalDate.now()));
        assertEquals(randomFutureDate, payment.getPaymentDate(),"Future paymentDate not set");
	}
	
	@Test
	//Check if 0 date, 01-01-1970, can be set in paymentDate
	void testSetZeroPaymentDate() {
		LocalDate zeroDate=LocalDate.ofEpochDay(0);
		payment.setPaymentDate(zeroDate);
        assertEquals(zeroDate, payment.getPaymentDate(),"Zero paymentDate not set");
	}
	
	@Test
	//Check if null can be set in paymentDate
	void testSetNullPaymentDate() {
		Exception exception= assertThrows(IllegalArgumentException.class, ()->{
			payment.setPaymentDate(null);
		},
		"paymentDate cannot be null"
		);
	}
	
	@Test
	//Check if 29th February can be set for leap years in paymentDate
	void testSetLeapYearPaymentDate() {
		LocalDate leapDate = LocalDate.of(2020, 2, 29);
		payment.setPaymentDate(leapDate);
        assertEquals(leapDate, payment.getPaymentDate(), "Leap year paymentDate not set");
	}
	
	@Test
	//Check if mode can be set
	void testSetMode() {
		String mode="Online";
		payment.setMode(mode);
		assertEquals(mode, payment.getMode());
	}
	
}
