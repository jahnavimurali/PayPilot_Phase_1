package com.paypilot.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;

public class BillCategoryParameterizedTest {

    @ParameterizedTest
    @CsvSource({
        "Electricity,1001,1500.0",
        "Water,1002,500.0",
        "Internet,1003,1200.0",
        "Gas,1004,800.0",
        "Rent,1005,10000.0"
    })
    void testBillCategories(String category, int billId, double amount) {
        Bill bill = new Bill(
                billId,
                1,                       // userId
                category + " Bill",      // billName
                category,
                LocalDate.now().plusDays(5),
                amount,
                false
        );

        // Validate category assignment
        Assertions.assertEquals(category, bill.getCategory());

        // Validate that amount is not negative
        Assertions.assertTrue(bill.getAmount() >= 0);

        // Validate that due date is in the future
        Assertions.assertTrue(bill.getDueDate().isAfter(LocalDate.now()));
    }
}

