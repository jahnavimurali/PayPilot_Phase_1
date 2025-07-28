package com.paypilot.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.paypilot.model.Payment;


public class PaymentTest {
    private Date testDate = new Date();

    @Test
    public void testEquals_SameValues_ReturnsTrue() {
        Payment payment1 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        Payment payment2 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        assertTrue(payment1.equals(payment2));
    }

    @Test
    public void testEquals_DifferentValues_ReturnsFalse() {
        Payment payment1 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        Payment payment2 = new Payment(2, 102, 50.0, testDate, "Debit Card");
        assertFalse(payment1.equals(payment2));
    }

    @Test
    public void testHashCode_EqualObjects_SameHashCode() {
        Payment payment1 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        Payment payment2 = new Payment(1, 101, 100.0, testDate, "Credit Card");
        assertEquals(payment1.hashCode(), payment2.hashCode());
    }
}