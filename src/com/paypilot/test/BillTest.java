package com.paypilot.test;

import com.paypilot.model.Bill;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    // Test data
    private final LocalDate testDate = LocalDate.of(2023, 12, 31);
    private final Bill bill1 = new Bill(1, 101, "Electricity", "Utilities", testDate, 100.0, true);
    private final Bill bill1Copy = new Bill(1, 101, "Electricity", "Utilities", testDate, 100.0, true);
    private final Bill bill2 = new Bill(2, 102, "Internet", "Services", testDate.plusMonths(1), 50.0, false);

    @Test
    public void testEquals_Reflexivity() {
        // A bill should equal itself
        assertTrue(bill1.equals(bill1));
    }

    @Test
    public void testEquals_Symmetry() {
        // If bill1 equals bill1Copy, then bill1Copy must equal bill1
        assertEquals(bill1.equals(bill1Copy), bill1Copy.equals(bill1));
    }

    @Test
    public void testEquals_Transitivity() {
        Bill bill3 = new Bill(1, 101, "Electricity", "Utilities", testDate, 100.0, true);
        // If bill1 equals bill1Copy and bill1Copy equals bill3, then bill1 must equal bill3
        assertTrue(bill1.equals(bill1Copy) && bill1Copy.equals(bill3) && bill1.equals(bill3));
    }

    @Test
    public void testEquals_Consistency() {
        // Multiple calls should consistently return the same result
        assertTrue(bill1.equals(bill1Copy));
        assertTrue(bill1.equals(bill1Copy)); // Same result on second call
    }

    @Test
    public void testEquals_NullComparison() {
        // A bill should not equal null
        assertFalse(bill1.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        // A bill should not equal an object of a different class
        assertFalse(bill1.equals("Not a Bill object"));
    }

    @Test
    public void testEquals_DifferentUserId() {
        Bill modified = new Bill(999, bill1.getBillId(), bill1.getBillName(), 
                              bill1.getCategory(), bill1.getDueDate(), 
                              bill1.getAmount(), bill1.isRecurring());
        assertFalse(bill1.equals(modified));
    }

    @Test
    public void testEquals_DifferentBillId() {
        Bill modified = new Bill(bill1.getUserId(), 999, bill1.getBillName(), 
                              bill1.getCategory(), bill1.getDueDate(), 
                              bill1.getAmount(), bill1.isRecurring());
        assertFalse(bill1.equals(modified));
    }

    @Test
    public void testEquals_DifferentAmount() {
        Bill modified = new Bill(bill1.getUserId(), bill1.getBillId(), bill1.getBillName(), 
                              bill1.getCategory(), bill1.getDueDate(), 
                              999.99, bill1.isRecurring());
        assertFalse(bill1.equals(modified));
    }

    @Test
    public void testHashCode_EqualObjectsSameHashCode() {
        // Equal objects must have equal hash codes
        assertEquals(bill1.hashCode(), bill1Copy.hashCode());
    }

    @Test
    public void testHashCode_Consistency() {
        // Multiple calls should return the same hash code
        int initialHashCode = bill1.hashCode();
        assertEquals(initialHashCode, bill1.hashCode());
    }

    @Test
    public void testHashCode_DifferentObjectsDifferentHashCodes() {
        // Different objects should ideally have different hash codes
        assertNotEquals(bill1.hashCode(), bill2.hashCode());
    }
}