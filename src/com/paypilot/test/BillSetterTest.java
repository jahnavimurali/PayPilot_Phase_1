package com.paypilot.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.paypilot.model.Bill;

class BillSetterTest {

	private Bill bill;
	
	@BeforeEach
	void setUp() {
		//create new bill object
		bill=new Bill(0, 0, "a", "a", LocalDate.now(), 0, false);
	}
	
	@Test
	//Check userId setter
	void testSetUserId() {
		int userId=12345;
		bill.setUserId(userId);
		System.out.println(bill.getUserId());
		assertEquals(userId, bill.getUserId(),"userId not set");
	}

	@Test
	//Check billId setter
	void testSetBillId() {
		int billId=12345;
		bill.setBillId(billId);
		assertEquals(billId, bill.getBillId(),"billId not set");
	}

	@Test
	//Check billName setter
	void testSetBillName() {
		String billName="Electricity";
		bill.setBillName(billName);
		assertEquals(billName, bill.getBillName(),"billName not set");
	}
	
	@Test
	//Check category setter
	void testSetCategory() {
		String category="Utilities";
		bill.setCategory(category);
		assertEquals(category, bill.getCategory(),"category not set");
	}

	@Test
	//Check if random date can be set in dueDate
	void testSetRandomDueDate() {
		LocalDate start = LocalDate.ofEpochDay(0);
        LocalDate end = LocalDate.now();
        long days = end.toEpochDay() - start.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = start.plusDays(randomDay);

        bill.setDueDate(randomDate);
        assertEquals(randomDate, bill.getDueDate(),"Random dueDate not set");
	}
	
	@Test
	//Check if random date from future can be set in dueDate
	void testSetFutureDueDate() {
		LocalDate start = LocalDate.now().plusDays(1);
	    LocalDate end = LocalDate.now().plusYears(10);
	    long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay() + 1);
	    LocalDate randomFutureDate = LocalDate.ofEpochDay(randomDay);

	    bill.setDueDate(randomFutureDate);
	    assertTrue(randomFutureDate.isAfter(LocalDate.now()));
        assertEquals(randomFutureDate, bill.getDueDate(),"Future dueDate not set");
	}
	
	@Test
	//Check if 0 date, 01-01-1970, can be set in dueDate
	void testSetZeroDueDate() {
		LocalDate zeroDate=LocalDate.ofEpochDay(0);
		bill.setDueDate(zeroDate);
		assertEquals(zeroDate, bill.getDueDate(),"Zero dueDate not set");
	}
	
	@Test
	//Check if 29th February can be set for leap years in dueDate
	void testSetLeapYearDueDate() {
		LocalDate leapDate = LocalDate.of(2020, 2, 29);
		bill.setDueDate(leapDate);
        assertEquals(leapDate, bill.getDueDate(), "Leap year dueDate not set");
	}

	@Test
	//Check if amount can be set to positive value
	void testSetAmount() {
		double billAmount=12345.83;
		bill.setAmount(billAmount);
		assertEquals(billAmount, bill.getAmount(),0.001,"Amount not set");
	}
	
	@Test
	//Check if isRecurring can be set and reset
	void testSetRecurring() {
		bill.setRecurring(true);
		assertTrue(bill.isRecurring(),"isRecurring not set to true");
		bill.setRecurring(false);
		assertFalse(bill.isRecurring(),"isRecurring not set to false");
	}
	
	

}


