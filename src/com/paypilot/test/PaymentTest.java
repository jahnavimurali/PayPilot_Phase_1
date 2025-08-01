package com.paypilot.test;

import com.paypilot.exception.InvalidPaymentDateException;
import com.paypilot.model.Bill;
import com.paypilot.model.Payment;
import com.paypilot.util.PaymentDateValidator;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    // Test data
    private final LocalDate testDate = LocalDate.of(2023, 12, 31);
    private final Payment payment1 = new Payment(1, 101, 100.0, testDate, "Credit Card");
    private final Payment payment1Copy = new Payment(1, 101, 100.0, testDate, "Credit Card");
    private final Payment payment2 = new Payment(2, 102, 50.0, testDate.plusDays(1), "Debit Card");
    private final Bill bill = new Bill(99, 99, "TestBill", "testCategory", testDate.plusDays(2), 0, false);

    // equals() tests
    @Test
    public void testEquals_Reflexivity() {
        assertTrue(payment1.equals(payment1), "A payment should equal itself");
    }

    @Test
    public void testEquals_Symmetry() {
        assertEquals(payment1.equals(payment1Copy), payment1Copy.equals(payment1), 
            "Equals should be symmetric");
    }

    @Test
    public void testEquals_Transitivity() {
        Payment payment3 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        assertTrue(payment1.equals(payment1Copy) && payment1Copy.equals(payment3) && payment1.equals(payment3),
            "Equals should be transitive");
    }

    @Test
    public void testEquals_Consistency() {
        boolean firstResult = payment1.equals(payment1Copy);
        boolean secondResult = payment1.equals(payment1Copy);
        assertEquals(firstResult, secondResult, "Multiple equals calls should return same result");
    }

    @Test
    public void testEquals_NullComparison() {
        assertFalse(payment1.equals(null), "Payment should not equal null");
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(payment1.equals("Not a Payment object"), "Payment should not equal different class");
    }

    @Test
    public void testEquals_DifferentPaymentId() {
        Payment modified = new Payment(999, payment1.getBillId(), payment1.getAmountPaid(),
                                    payment1.getPaymentDate(), payment1.getMode());
        assertFalse(payment1.equals(modified), "Different paymentId should make payments unequal");
    }

    @Test
    public void testEquals_DifferentBillId() {
        Payment modified = new Payment(payment1.getPaymentId(), 999, payment1.getAmountPaid(),
                                    payment1.getPaymentDate(), payment1.getMode());
        assertFalse(payment1.equals(modified), "Different billId should make payments unequal");
    }

    @Test
    public void testEquals_DifferentAmount() {
        Payment modified = new Payment(payment1.getPaymentId(), payment1.getBillId(), 999.99,
                                    payment1.getPaymentDate(), payment1.getMode());
        assertFalse(payment1.equals(modified), "Different amountPaid should make payments unequal");
    }

    @Test
    public void testEquals_DifferentDate() {
        Payment modified = new Payment(payment1.getPaymentId(), payment1.getBillId(), payment1.getAmountPaid(),
                                    testDate.plusDays(10), payment1.getMode());
        assertFalse(payment1.equals(modified), "Different paymentDate should make payments unequal");
    }

    @Test
    public void testEquals_DifferentMode() {
        Payment modified = new Payment(payment1.getPaymentId(), payment1.getBillId(), payment1.getAmountPaid(),
                                    payment1.getPaymentDate(), "Cash");
        assertFalse(payment1.equals(modified), "Different mode should make payments unequal");
    }

    // hashCode() tests
    @Test
    public void testHashCode_EqualObjectsSameHashCode() {
        assertEquals(payment1.hashCode(), payment1Copy.hashCode(),
            "Equal payments must have equal hash codes");
    }

    @Test
    public void testHashCode_Consistency() {
        int initialHashCode = payment1.hashCode();
        assertEquals(initialHashCode, payment1.hashCode(),
            "Multiple hashCode calls should return same value");
    }

    @Test
    public void testHashCode_DifferentObjectsDifferentHashCodes() {
        assertNotEquals(payment1.hashCode(), payment2.hashCode(),
            "Different payments should have different hash codes");
    }
    
    @Test
    public  void testValidateDate() {
    	assertThrows(InvalidPaymentDateException.class,  
    			() -> PaymentDateValidator.validatePaymentDate(bill, payment1)
    	);
    }
}